package ba.unsa.etf.rpr.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public abstract class AbstractDao<T> implements dao<T>{
    private Connection conn;
    private String tableName;
    //one constructor that takes a string tableName as parameter
    //and initializes the connection to the database
    public AbstractDao(String tableName) {
        try {
            this.tableName = tableName;
            Properties p = new Properties();
            p.load(ClassLoader.getSystemResource("conn.properties").openStream());
            this.conn = DriverManager.getConnection(p.getProperty("db_url"), p.getProperty("username"), p.getProperty("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        return conn;
    }

    //one method that returns the name of the table
    public String getTableName() {
        return tableName;
    }



    //implements the methods from the dao interface
    //getById, add, update, delete, getAll
    public T getById(int id) {
        return executeQueryUnique("Select * from " + getTableName() + " where id  = ?",new Object[id]);
    }
    public T add(T item){
        Map<String, Object> row = object2row(item);
        StringBuilder upitBuilder = new StringBuilder("INSERT INTO " + tableName + " (");
        for (String key : row.keySet()) {
            upitBuilder.append(key).append(",");
        }
        String upit = upitBuilder.toString();
        upit = upit.substring(0, upit.length() - 1);
        upit += ") VALUES (";
        StringBuilder upitBuilder1 = new StringBuilder(upit);
        for (String key : row.keySet()) {
            upitBuilder1.append("?,");
        }
        upit = upitBuilder1.toString();
        upit = upit.substring(0, upit.length() - 1);
        upit += ")";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            for (String key : row.keySet()) {
                stmt.setObject(i, row.get(key));
                i++;
            }
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return item;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public T update(T item){
        Map<String, Object> row = object2row(item);
        StringBuilder upitBuilder = new StringBuilder("UPDATE " + tableName + " SET ");
        for (String key : row.keySet()) {
            upitBuilder.append(key).append("=?,");
        }
        String upit = upitBuilder.toString();
        upit = upit.substring(0, upit.length() - 1);
        upit += " WHERE id = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(upit);
            int i = 1;
            for (String key : row.keySet()) {
                stmt.setObject(i, row.get(key));
                i++;
            }
            stmt.setObject(i, row.get("id" + tableName));
            stmt.executeUpdate();
            return getById((int) row.get("id" + tableName));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void delete(int id){
        String upit = "DELETE FROM " + tableName + " WHERE id = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(upit);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<T> getAll(){
        List<T> lista = new ArrayList<>();
        String upit = "SELECT * FROM " + tableName;
        try {
            PreparedStatement stmt = this.conn.prepareStatement(upit);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(row2object(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
    public List<T> executeQuery(String query, Object[] params){
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            if (params != null){
                for(int i = 1; i <= params.length; i++){
                    stmt.setObject(i, params[i-1]);
                }
            }
            ResultSet rs = stmt.executeQuery();
            ArrayList<T> resultList = new ArrayList<>();
            while (rs.next()) {
                resultList.add(row2object(rs));
            }
            return resultList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public T executeQueryUnique(String query, Object[] params) {
        List<T> result = executeQuery(query, params);
        if (result != null && result.size() == 1){
            return result.get(0);
        }else{
            return null;
        }
    }
    public abstract Map<String, Object> object2row(T object);
    public abstract T row2object(ResultSet rs) ;

}
