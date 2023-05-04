package mx.uv.fei.bussinesslogic;

import mx.uv.fei.logic.DeliverableFile;
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
public class DeliverableFileDAOTest {
    
    public DeliverableFileDAOTest() {
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
    public void testAddDeliverableFile() throws Exception {
        System.out.println("addDeliverableFile");
        DeliverableFile deliverableFile = new DeliverableFile();
        DeliverableFileDAO instance = new DeliverableFileDAO();
        
        deliverableFile.setName("Prueba");
        deliverableFile.setPathName("Esto es una prueba"); 
        deliverableFile.setType(".prueba");
        
        int expResult = 1;
        int result = instance.addDeliverableFile(deliverableFile);
        assertEquals(expResult, result);

    }


    @Test
    public void testGetUser() throws Exception {
        System.out.println("getUser");
        String idDeliverableFile = "";
        DeliverableFileDAO instance = new DeliverableFileDAO();
        DeliverableFile expResult = null;
        DeliverableFile result = instance.getUser(idDeliverableFile);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
