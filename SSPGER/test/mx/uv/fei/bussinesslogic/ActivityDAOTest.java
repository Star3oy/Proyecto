package mx.uv.fei.bussinesslogic;

import mx.uv.fei.implementations.ActivityDAO;
import java.util.Date;
import java.time.LocalDate;
import mx.uv.fei.logic.Activity;
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
public class ActivityDAOTest {
    
    public ActivityDAOTest() {
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
    public void testAddActivity() throws Exception {
        System.out.println("addActivity");
        Activity activity = new Activity();
        ActivityDAO activityDAO = new ActivityDAO();
        Date dateTest = new Date();
        java.sql.Date sqldateTest = new java.sql.Date(dateTest.getTime());
 
    
        activity.setTitle("Actividad de prueba");
        activity.setStatus(1);
        activity.setDetails("Esta es una prueba ");
        activity.setStartDate(sqldateTest);
        activity.setFinishDate(sqldateTest);
        
        int expResult = 1;
        int result = activityDAO.addActivity(activity);
        assertEquals(expResult, result);
    }


    @Test
    public void testConvertLocalDateToDate() {
        System.out.println("convertLocalDateToDate");
        LocalDate date = null;
        ActivityDAO instance = new ActivityDAO();
        Date expResult = null;
        Date result = instance.convertLocalDateToDate(date);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
