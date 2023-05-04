package mx.uv.fei.bussinesslogic;

import mx.uv.fei.logic.Login;
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
public class LoginDAOTest {
    
    public LoginDAOTest() {
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

    /**
     * Test of addLogin method, of class LoginDAO.
     */
    @Test
    public void testAddLogin() throws Exception {
        System.out.println("addLogin");
        Login login = new Login();
        LoginDAO instance = new LoginDAO();
        
        login.setIdUser("456prueba");
        login.setPassword("Patito123prueba");
        
        int expResult = 1;
        int result = instance.addLogin(login);
        assertEquals(expResult, result);

    }
    
}
