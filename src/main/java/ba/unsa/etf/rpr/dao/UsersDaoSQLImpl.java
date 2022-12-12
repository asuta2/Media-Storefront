package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.mn.Users;

import java.sql.*;
import java.util.ArrayList;
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
        String upit = "Select * from Users where idUsers = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(upit);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            {
                Users user = new Users();
                user.setIdUsers(rs.getInt("idUsers"));
                user.setUsername(rs.getString("Username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setBirthDate(rs.getDate("BirthDate"));
                user.setBalance(rs.getDouble("Balance"));
                user.setPrivilegeLevel(rs.getString("PrivilegeLevel"));

                rs.close();
                return user;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Users add(Users item) {
        String upit = "INSERT INTO Users(email,password,Username,BirthDate,Balance,PPrivilegeLevel) VALUES(?,?,?,?,?,?)";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getEmail());
            stmt.setString(2, item.getPassword());
            stmt.setString(3, item.getUsername());
            stmt.setDate(4, item.getBirthDate());
            stmt.setDouble(5, item.getBalance());
            stmt.setString(6, item.getPrivilegeLevel());
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
        String upit = "DELETE FROM Users where idUsers = ?";
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
        String upit = "SELECT * FROM Users";
        List<Users> ispis = new ArrayList<>();
        try{
            PreparedStatement stmt = this.conn.prepareStatement(upit);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                Users user = new Users();
                user.setIdUsers(rs.getInt("idUsers"));
                user.setUsername(rs.getString("Username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setBirthDate(rs.getDate("BirthDate"));
                user.setBalance(rs.getDouble("Balance"));
                user.setPrivilegeLevel(rs.getString("PrivilegeLevel"));
                ispis.add(user);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ispis;
    }
}
