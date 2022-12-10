package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.mn.Users;
import ba.unsa.etf.rpr.dao.UsersDao;
import ba.unsa.etf.rpr.dao.UsersDaoSQLImpl;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        UsersDao dao = new UsersDaoSQLImpl();
        Users test = new Users();
        test.setUsername("Hasim");
        test.setEmail("Hasim@hasim");
        test.setPassword("Ne");
        dao.add(test);
        List<Users> owo=dao.getAll();
        for(Users x: owo)
        {
            System.out.println(owo.get(0).getUsername());
        }


    }
}
