package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.mn.Users;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class UsersManagerTest {
    /**
     * Test if know to be already existing user is found in database
     */
    private UsersManager usersManager = new UsersManager();

    @Test
    void checkUserTest() {
        assertTrue(usersManager.checkUser("admin@admin.com","admin"));
        assertFalse(usersManager.checkUser("admin","admin1"));
    }
    @Test
    void getUserByIdTest(){
        //Test case for when non-existing user is searched
        Users temp = usersManager.getUserById(55);
        assertNull(temp);
        //Test case for when existing user is searched
        assertTrue(usersManager.getUserById(9).getUsername().equals("admin"));
    }
    @Test
    void getAllTest(){
        List<Users> users = usersManager.getAll();
        assertNotNull(users);
        assertEquals(1,users.get(0).getIdUsers());
    }

}
