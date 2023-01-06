package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.mn.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UsersDaoSQLImpl extends AbstractDao<Users> implements UsersDao {
    //private Connection conn;

    public UsersDaoSQLImpl() {
        super("Users");
    }

    @Override
    public Map<String, Object> object2row(Users object) {
        return Map.of("username", object.getUsername(), "email", object.getEmail(), "password", object.getPassword(), "PrivilegeLevel", object.getPrivilegeLevel(), "Balance", object.getBalance());
    }
    @Override
    public Users update(Users object) {
        String sql = "UPDATE Users SET username = ?, email = ?, password = ?, PrivilegeLevel = ?, Balance = ? WHERE idUsers = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, object.getUsername());
            stmt.setString(2, object.getEmail());
            stmt.setString(3, object.getPassword());
            stmt.setString(4, object.getPrivilegeLevel());
            stmt.setDouble(5, object.getBalance());
            stmt.setInt(6, object.getIdUsers());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
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
        try {
            Users temp = executeQueryUnique("SELECT * FROM Users where email = ? and password = ?",new Object[]{email,password});
            if (temp!=null) return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkUsername(String username) {

        Users temp = executeQueryUnique("SELECT * FROM Users where username = ?",new Object[]{username});
        return temp != null;

    }

    @Override
    public boolean checkEmail(String email) {
        try {
            Users temp = executeQueryUnique("SELECT * FROM Users where email = ?",new Object[]{email});
            if (temp!=null) return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Users getUserByEmail(String username) {
        return executeQueryUnique("SELECT * FROM Users WHERE email = ?", new Object[]{username});
    }


    @Override
    public Users getUserByUsername(String username) {
        return executeQueryUnique("SELECT * FROM Users WHERE username = ?", new Object[]{username});
    }

}
