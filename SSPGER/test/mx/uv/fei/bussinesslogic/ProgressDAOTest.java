package mx.uv.fei.bussinesslogic;

import java.util.ArrayList;
import java.util.List;
import mx.uv.fei.implementations.ProgressDAO;
import mx.uv.fei.logic.DeliverableFile;
import mx.uv.fei.logic.Progress;
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
public class ProgressDAOTest {
    
    public ProgressDAOTest() {
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
    public void testGetAllProgressesByActivity() throws Exception {
        System.out.println("getAllProgresses");
        int idActivity = 2;
        List<Progress> expResult = new ArrayList<>();
        Progress progress = new Progress();
        progress.setIdProgress(1);
        progress.setComent("hola ");
        progress.setProgressDate(null);
        progress.setIdUser("zs21013862");
        progress.setStatus(1);
        progress.setIdActivity(2);
        expResult.add(progress);
        
        Progress progressTest = new Progress();
        progressTest.setIdProgress(2);
        progressTest.setComent("adios ");
        progressTest.setProgressDate(null);
        progressTest.setIdUser("zs21013862");
        progressTest.setStatus(0);
        progressTest.setIdActivity(2);
        expResult.add(progressTest);
        
        ProgressDAO instance = new ProgressDAO();
        List<Progress> result = instance.getAllProgressesByActivity(idActivity);
        assertEquals(expResult, result);
    }

    /**
     * Test of getProgressesByStatus method, of class ProgressDAO.
     */
    @Test
    public void testGetProgressesByStatus() throws Exception {
        System.out.println("getProgressesByStatus");
        int idActivity = 0;
        int status = 0;
        ProgressDAO instance = new ProgressDAO();
        List<Progress> expResult = null;
        List<Progress> result = instance.getProgressesByStatus(idActivity, status);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendProgressTransaction method, of class ProgressDAO.
     */
    @Test
    public void testSendProgressTransaction() throws Exception {
        System.out.println("sendProgressTransaction");
        Progress progress = null;
        DeliverableFile file = null;
        ProgressDAO instance = new ProgressDAO();
        int expResult = 0;
        int result = instance.sendProgressTransaction(progress, file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testgetStudentComment() throws Exception {
        System.out.println("sendProgressTransaction");
        String expResult = "Probando";
        ProgressDAO progressDAO = new ProgressDAO();
        String result = progressDAO.getStudentComment(3);
        assertEquals(expResult, result);
    }
    
}
