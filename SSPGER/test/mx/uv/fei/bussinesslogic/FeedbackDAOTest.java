package mx.uv.fei.bussinesslogic;

import mx.uv.fei.implementations.FeedbackDAO;
import java.util.Date;
import mx.uv.fei.logic.Feedback;
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
public class FeedbackDAOTest {
    
    public FeedbackDAOTest() {
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
    public void testAddFeedback() throws Exception {
        System.out.println("addFeedback");
        
        Date actualDate = new  Date();
        java.sql.Date sqldate = new java.sql.Date(actualDate.getTime());
        Feedback feedback = new Feedback();
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        
        feedback.setComent("Esto es una prueba 2");
        feedback.setFeedBackDate(sqldate);
        
        int expResult = 1;
        int result = feedbackDAO.addFeedback(feedback);
        assertEquals(expResult, result);

    }
    
}
