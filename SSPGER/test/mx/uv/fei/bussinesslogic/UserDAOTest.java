package mx.uv.fei.bussinesslogic;


import java.util.List;
import mx.uv.fei.logic.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import mx.uv.fei.implementations.UserDAO;
import static org.junit.Assert.*;

/**
 *
 * @author Star3oy
 */
public class UserDAOTest {
    
    public UserDAOTest() {
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
     * Test of registerUser method, of class UserDAO.
     */
    @Test
    public void testAddUser() throws Exception {
        System.out.println("registerUser");
        User user = new User();
        UserDAO instance = new UserDAO();
        
        user.setIdUser("Prueba123");
        user.setFirstName("prueba123");
        user.setMiddleName("prueba123");
        user.setLastName("prueba123");
        user.setInstitutionalEmail("prueba123");
        user.setIdStatus(1);
        user.setIdRole(4);
        
        int expResult = 1;
        int result = instance.addUser(user);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserList method, of class UserDAO.
     */
    @Test
    public void testGetUserList() throws Exception {
        System.out.println("getUserList");
        
        User user  = new User(); 
        user.setIdUser("Prueba123");
        user.setFirstName("Prueba123");
        user.setMiddleName("Prueba123");
        user.setLastName("Prueba123");
        user.setInstitutionalEmail("Prueba123");
        user.setIdStatus(1);
        user.setIdRole(4); 
        
        User userAux = new User();
        userAux.setIdUser("zs21013882");
        userAux.setFirstName("Eduardo");
        userAux.setMiddleName("Carrera");
        userAux.setLastName("Colorado");
        userAux.setInstitutionalEmail("zs21013862@estudiantes.uv.mx");
        userAux.setIdStatus(1);
        userAux.setIdRole(4);

        UserDAO instance = new UserDAO();
        List<User> expResult = new ArrayList<>();
        expResult.add(user);
        expResult.add(userAux);
    
        List<User> result = instance.getUserList();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUser method, of class UserDAO.
     */
    @Test
    public void testGetUser() throws Exception {
        System.out.println("getUser");

        User expResult  = new User();
        expResult.setIdUser("Prueba123");
        expResult.setFirstName("prueba123");
        expResult.setMiddleName("prueba123");
        expResult.setLastName("prueba123");
        expResult.setInstitutionalEmail("prueba123");
        expResult.setIdStatus(1);
        expResult.setIdRole(4);
        
        UserDAO instance = new UserDAO();
        User result = instance.getUser(expResult.getIdUser());

        assertEquals(expResult, result);

    }
    
    @Test
    public void testModifyUser() throws Exception {
        System.out.println("Modify User");
     
        User user = new User();
        UserDAO instance = new UserDAO();
        
        user.setIdUser("prueba45");
        user.setFirstName("Aldo2");
        user.setMiddleName("Colorado2");
        user.setLastName("Carrera2");
        user.setInstitutionalEmail("2Prueba@estudiantes.uv.mx");
        user.setIdStatus(1);
        user.setIdRole(4);
        
        int expResult = 1;
        String idUser = "zs21013882";
        int result = instance.modifyUser(user, idUser);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDisableUser() throws Exception {
        System.out.println("Disable User");

        UserDAO instance = new UserDAO();
        
        int expResult = 1;
        String idUser = "zs21013882";
        int result = instance.disableUser(idUser);
        
        assertEquals(expResult, result);
    }
    
    @Test
    public void testVerifyUserExistence() throws Exception {
        System.out.println("Disable User");
        User user = new User();
        UserDAO instance = new UserDAO();   
        
        int expResult = 0;
        
        user.setIdUser("zs2101382");
        user.setFirstName("Eduardo");
        user.setMiddleName("Carrera");
        user.setLastName("Colorado");
        user.setInstitutionalEmail("zs21013862@estudiantes.uv.mx");
        user.setIdStatus(1);
        user.setIdRole(4);
        
        int result = instance.verifyUserExistence(user);
        
        assertEquals(expResult, result);
        
    }
   
    
}
