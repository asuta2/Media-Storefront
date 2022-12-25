package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.mn.Users;

import java.util.List;

public class UsersManager {
    public void delete(int id){
        DaoFactory.getUsersDao().delete(id);
    }
    public Users add(Users user){
        return DaoFactory.getUsersDao().add(user);
    }
    public Users update(Users user){
        return DaoFactory.getUsersDao().update(user);
    }
    public Users getById(int id){
        return DaoFactory.getUsersDao().getById(id);
    }
    public List<Users> getAll(){
        return DaoFactory.getUsersDao().getAll();
    }
    public boolean checkUser(String email, String password) {
        return DaoFactory.getUsersDao().checkUser(email, password);
    }
    public boolean checkUsername(String username) {
        return DaoFactory.getUsersDao().checkUsername(username);
    }
    public boolean checkEmail(String email) {
        return DaoFactory.getUsersDao().checkEmail(email);
    }
    public Users getUserByEmail(String username) {
        return DaoFactory.getUsersDao().getUserByEmail(username);
    }
}
