package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.mn.Users;

public interface UsersDao extends dao<Users>{
    boolean checkUser(String email, String password);
    boolean checkUsername(String username);
    boolean checkEmail(String email);
    Users getUserByEmail(String username);
    Users getUserByUsername(String username);
    Users editProfile(Users temp);
    Users getUserById(int id);
}
