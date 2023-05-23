package mx.uv.fei.contracts;

import java.sql.SQLException;
import java.util.List;
import mx.uv.fei.logic.Course;
import mx.uv.fei.logic.User;
import mx.uv.fei.logic.UserTable;

/**
 *
 * @author Sue
 */
public interface ICourse {
    int registerCourse (Course course, List<UserTable> selectedUsers) throws SQLException;
    Course getCourseById (int idCourse) throws SQLException;
    Boolean isCourseRegistered (Course course) throws SQLException;
    int diseableCourse(int idCourse) throws SQLException;
    int modifyCourse(Course course, List<UserTable> selectedUsers, int idCourse) throws SQLException;
    int getStatusCourse(Course course) throws SQLException;
    Boolean isNrcActive (Course course) throws SQLException;
    User getProfessorOfCourse(int idCourse) throws SQLException;
    Course getCourseByNrc(String nrc) throws SQLException;
    List<User> getStudentsOfCourse(int idCourse) throws SQLException;
    public int numberOfActivities (String idUser) throws SQLException;
    public int numberOfActivitiesCompleted (String idUser) throws SQLException;
    public String getReceptionalWorkName (String idUser) throws SQLException;
}
