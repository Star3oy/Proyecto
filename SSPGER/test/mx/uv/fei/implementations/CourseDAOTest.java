package mx.uv.fei.implementations;

import java.util.List;
import mx.uv.fei.logic.Course;
import mx.uv.fei.logic.User;
import mx.uv.fei.logic.UserTable;
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
public class CourseDAOTest {
    
    public CourseDAOTest() {
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
    public void testRegisterCourse() throws Exception {
        System.out.println("registerCourse");
        Course course = null;
        List<UserTable> selectedUsers = null;
        CourseDAO instance = new CourseDAO();
        int expResult = 0;
        int result = instance.registerCourse(course, selectedUsers);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testModifyCourse() throws Exception {
        System.out.println("modifyCourse");
        Course course = null;
        List<UserTable> selectedUsers = null;
        int idCourse = 0;
        CourseDAO instance = new CourseDAO();
        int expResult = 0;
        int result = instance.modifyCourse(course, selectedUsers, idCourse);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetCourseById() throws Exception {
        System.out.println("getCourseById");
        int idCourse = 0;
        CourseDAO instance = new CourseDAO();
        Course expResult = null;
        Course result = instance.getCourseById(idCourse);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetCourseByNrc() throws Exception {
        System.out.println("getCourseByNrc");
        String nrc = "";
        CourseDAO instance = new CourseDAO();
        Course expResult = null;
        Course result = instance.getCourseByNrc(nrc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testIsCourseRegistered() throws Exception {
        System.out.println("isCourseRegistered");
        Course course = null;
        CourseDAO instance = new CourseDAO();
        Boolean expResult = null;
        Boolean result = instance.isCourseRegistered(course);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testDiseableCourse() throws Exception {
        System.out.println("diseableCourse");
        int idCourse = 0;
        CourseDAO instance = new CourseDAO();
        int expResult = 0;
        int result = instance.diseableCourse(idCourse);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetStatusCourse() throws Exception {
        System.out.println("getStatusCourse");
        Course course = null;
        CourseDAO instance = new CourseDAO();
        int expResult = 0;
        int result = instance.getStatusCourse(course);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testIsNrcActive() throws Exception {
        System.out.println("isNrcActive");
        Course course = null;
        CourseDAO instance = new CourseDAO();
        Boolean expResult = null;
        Boolean result = instance.isNrcActive(course);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetProfessorOfCourse() throws Exception {
        System.out.println("getProfessorOfCourse");
        int idCourse = 0;
        CourseDAO instance = new CourseDAO();
        User expResult = null;
        User result = instance.getProfessorOfCourse(idCourse);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetStudentsOfCourse() throws Exception {
        System.out.println("getStudentsOfCourse");
        int idCourse = 0;
        CourseDAO instance = new CourseDAO();
        List<User> expResult = null;
        List<User> result = instance.getStudentsOfCourse(idCourse);
        assertEquals(expResult, result);
    }

    @Test
    public void testNumberOfActivities() throws Exception {
        System.out.println("numberOfActivities");
        String idUser = "";
        CourseDAO instance = new CourseDAO();
        int expResult = 0;
        int result = instance.numberOfActivities(idUser);
        assertEquals(expResult, result);
    }

    @Test
    public void testNumberOfActivitiesCompleted() throws Exception {
        System.out.println("numberOfActivitiesCompleted");
        String idUser = "";
        CourseDAO instance = new CourseDAO();
        int expResult = 0;
        int result = instance.numberOfActivitiesCompleted(idUser);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetReceptionalWorkName() throws Exception {
        System.out.println("getReceptionalWorkName");
        String idUser = "zs21nose";
        CourseDAO instance = new CourseDAO();
        String expResult = "PruebaConcluir";
        String result = instance.getReceptionalWorkName(idUser);
        assertEquals(expResult, result);
    }
    
}
