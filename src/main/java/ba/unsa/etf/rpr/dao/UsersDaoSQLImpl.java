package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.mn.Users;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class UsersDaoSQLImpl implements UsersDao{
    private Connection conn;
    static final String DB_URL = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7583670";
    static final String USER = "sql7583670";
    static final String PASS = "KVxzvQ1ssg";

    public UsersDaoSQLImpl() {
        try{
            this.conn= DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Users getById(int id) {
        return null;
    }

    @Override
    public Users add(Users item) {
        String upit = "INSERT INTO Users(email,password,Username) VALUES(?,?,?)";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getEmail());
            stmt.setString(2, item.getPassword());
            stmt.setString(3, item.getUsername());
            stmt.executeUpdate();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Users update(Users item) {
        return null;
    }

    @Override
    public void delete(int id) {
        String upit = "DELETE FROM Users where id = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1,id);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Users> getAll() {
        return null;
    }
}
