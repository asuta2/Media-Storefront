package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.mn.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class UsersDaoSQLImpl extends AbstractDao<Users> implements UsersDao {
    //private Connection conn;

    public UsersDaoSQLImpl() {
        super("Users");
    }

    @Override
    public Map<String, Object> object2row(Users object) {
        return null;
    }

    @Override
    public Users row2object(ResultSet rs) {
        try{
            Users temp=new Users();
            temp.setIdUsers(rs.getInt(1));
            temp.setUsername(rs.getString(2));
            temp.setEmail(rs.getString(3));
            temp.setPassword(rs.getString(4));
            temp.setPrivilegeLevel(rs.getString(6));
            temp.setBalance(rs.getDouble(5));
            return temp;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public boolean checkUser(String email, String password) {
        String upit = "SELECT * FROM Users where email = ? and password = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(upit);
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

    @Override
    public Users getUserByEmail(String username) {
        return executeQueryUnique("SELECT * FROM Users WHERE email = ?", new Object[]{username});
    }

    private boolean provjeraHelper(String email, String upit) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(upit);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return true;
        } catch (Exception e) {
            e.printStackTrace();
    }
        return false;
    }
}
