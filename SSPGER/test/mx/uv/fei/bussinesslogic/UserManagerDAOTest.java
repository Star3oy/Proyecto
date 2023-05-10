package mx.uv.fei.bussinesslogic;

import mx.uv.fei.implementations.UserManagerDAO;
import mx.uv.fei.logic.Login;
import mx.uv.fei.logic.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Star3oy
 */
public class UserManagerDAOTest {
    
    public UserManagerDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    @Test
    public void testUserAdditionTransition() throws Exception {
        System.out.println("userAdditionTransition");
        User user = new User();
        Login login = new Login();
        UserManagerDAO instance = new UserManagerDAO();
        
        user.setIdUser("pruebaTransicion2");
        user.setFirstName("pruebaTransicion2");
        user.setMiddleName("pruebaTransicion2");
        user.setLastName("pruebaTransicion2");
        user.setInstitutionalEmail("pruebaTransicion2");
        user.setType(1);
        user.setIdStatus(1);
    
        login.setIdLogin(9);
        login.setIdUser("123pruebaTransicón2");
        login.setPassword("Patito123pruebaTransición2");
      
        int expResult = 2;
        int result = instance.userAdditionTransition(user, login);
        
        assertEquals(expResult, result);

    }
    
}
