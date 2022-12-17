package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.mn.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoSQLImpl implements UsersDao {
    private Connection conn;
    static final String DB_URL = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7583670";
    static final String USER = "sql7583670";
    static final String PASS = "KVxzvQ1ssg";

    public UsersDaoSQLImpl() {
        try {
            this.conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Users getById(int id) {
        String upit = "Select * from Users where idUsers = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(upit);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Users user = new Users();
                user.setIdUsers(rs.getInt("idUsers"));
                user.setUsername(rs.getString("Username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
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
        String upit = "INSERT INTO Users(email,password,Username,Balance,PrivilegeLevel) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getEmail());
            stmt.setString(2, item.getPassword());
            stmt.setString(3, item.getUsername());
            stmt.setDouble(4, item.getBalance());
            stmt.setString(5, item.getPrivilegeLevel());
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
        try {
            PreparedStatement stmt = this.conn.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Users> getAll() {
        String upit = "SELECT * FROM Users";
        List<Users> ispis = new ArrayList<>();
        try {
            PreparedStatement stmt = this.conn.prepareStatement(upit);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Users user = new Users();
                user.setIdUsers(rs.getInt("idUsers"));
                user.setUsername(rs.getString("Username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
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

    @Override
    public boolean checkUser(String email, String password) {
        String upit = "SELECT * FROM Users where email = ? and password = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(upit);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkUsername(String username) {
        String upit = "SELECT * FROM Users where Username = ?";
        return provjeraHelper(username, upit);
    }

    @Override
    public boolean checkEmail(String email) {
        String upit = "SELECT * FROM Users where email = ?";
        return provjeraHelper(email, upit);
    }

    private boolean provjeraHelper(String email, String upit) {
        try {
            PreparedStatement stmt = this.conn.prepareStatement(upit);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return true;
        } catch (Exception e) {
            e.printStackTrace();
    }
        return false;
    }
}
