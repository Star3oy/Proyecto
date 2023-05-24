package mx.uv.fei.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mx.uv.fei.contracts.ICourse;
import mx.uv.fei.dataaccess.DataBaseManager;
import mx.uv.fei.logic.Course;
import mx.uv.fei.logic.User;
import mx.uv.fei.logic.UserTable;

/**
 *
 * @author Sue
 */
public class CourseDAO implements ICourse{
    private final String ADD_COURSE_COMMAND = "INSERT INTO cursos(nombre, nrc, estado, idSeccion, "
                + "idBloque, idPeriodoEscolar, idExperienciaEducativa) VALUES(?,?,?,?,?,?,?)";
    private final String ADD_USERS_TO_COURSE_QUERY = "INSERT INTO usuarios_cursos(idUsuario, idCurso) VALUES(?,?)";
    private final String GET_COURSE_QUERRY = "SELECT * FROM cursos WHERE idCurso = ?";
    private final String GET_COURSE_BY_NRC_QUERY = "SELECT * FROM cursos WHERE nrc = ?";
    private final String VERIFY_COURSE_REGISTERED_QUERY = "SELECT * FROM cursos WHERE (nombre = ? AND nrc = ?) AND (estado = ? AND idSeccion = ?)"
                + "AND (idBloque = ? AND idPeriodoEscolar = ?) AND idExperienciaEducativa = ?";
    private final String DISEABLE_COURSE_COMMAND = "UPDATE cursos SET estado = 0 WHERE idCurso = ?";
    private final String MODIFY_PROFESSOR_IN_COURSE_COMMAND = "UPDATE Usuarios_Cursos SET IdUsuario = ? WHERE IdCurso = ? AND IdUsuario = ?";
    private final String MODIFY_COURSE_COMMAND = "UPDATE cursos SET nombre = ?, nrc = ?, idSeccion = ?, idBloque = ?, idPeriodoEscolar = ?, "
                + "idExperienciaEducativa = ? WHERE idCurso = ?";
    private final String GET_STATUS_COURSE_QUERY = "SELECT estado FROM cursos WHERE idCurso = ?";
    private final String VERIFY_COURSE_ACTIVE_COMMAND = "SELECT * FROM cursos WHERE nrc = ? AND estado = 1";
    private final String GET_PROFESSOR_OF_COURSE_QUERY = "SELECT usuarios.* FROM usuarios JOIN usuarios_cursos ON usuarios.idUsuario = usuarios_cursos.idUsuario "
                + "JOIN cursos ON usuarios_cursos.IdCurso = cursos.IdCurso WHERE cursos.IdCurso = ? AND usuarios.tipoUsuario = 0";
    private final String GET_STUDENTS_OF_COURSE_QUERY = "SELECT usuarios.* FROM usuarios JOIN usuarios_cursos ON usuarios.idUsuario = usuarios_cursos.idUsuario "
                + "JOIN cursos ON usuarios_cursos.IdCurso = cursos.IdCurso WHERE cursos.IdCurso = ? AND usuarios.tipoUsuario = 1";
    
    private final String GET_NUMBER_OF_ACTIVITIES = "SELECT usuarios_cursos.idUsuario, COUNT(actividades.idActividad) AS numberOfActivities FROM usuarios_cursos" +
                " JOIN actividades ON usuarios_cursos.idUsuario = actividades.idUsuario WHERE usuarios_cursos.idUsuario = ? GROUP BY usuarios_cursos.idUsuario";
    
    private final String GET_NUMBER_OF_ACTIVITIES_COMPLETED = "SELECT usuarios_cursos.idUsuario, COUNT(actividades.idActividad) AS numberActivitiesCompleted FROM usuarios_cursos" +
               " INNER JOIN actividades ON usuarios_cursos.idUsuario = actividades.idUsuario WHERE usuarios_cursos.idUsuario = ? AND actividades.estado = 1 GROUP BY usuarios_cursos.idUsuario";
    
    private final String GET_RECEPCTIONAL_WORK_NAME_BY_STUDENT_QUERY = "SELECT trabajos_recepcionales.nombre FROM trabajos_recepcionales INNER JOIN usuarios_cursos ON trabajos_recepcionales.idTesista = usuarios_cursos.idUsuario" +
                " WHERE trabajos_recepcionales.idTesista = ?";
 
    @Override
    public int registerCourse (Course course, List<UserTable> selectedUsers) throws SQLException {
        int result;
        int ACTIVE = 1;
        ResultSet generatedKeys = null;
        
        try {
            DataBaseManager.getConnection().setAutoCommit(false);
            String query = ADD_COURSE_COMMAND;
            PreparedStatement statement = DataBaseManager.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, course.getName());
            statement.setString(2, course.getNrc());
            statement.setInt(3,ACTIVE);
            statement.setInt(4,course.getIdSection());
            statement.setInt(5, course.getIdBlock());
            statement.setInt(6, course.getIdSchoolPeriod());
            statement.setInt(7, course.getIdEducationalExperience());
            result = statement.executeUpdate();
            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int courseId = generatedKeys.getInt(1);
                course.setIdCourse(courseId);
            }
            insertUsersInCourse(course.getIdCourse(), selectedUsers);
            DataBaseManager.getConnection().commit();
        } catch (SQLException sQLException) {
            DataBaseManager.rollback();
            throw sQLException;
        } finally {
            if (generatedKeys != null) {
                generatedKeys.close();
            }
            DataBaseManager.closeConnection();
        }
        return result;      
    }
    
    @Override
    public int modifyCourse(Course course, List<UserTable> selectedUsers, int idCourse) throws SQLException {
        int RESULT = 0;
        
        try {
            DataBaseManager.getConnection().setAutoCommit(false);
            String query = MODIFY_COURSE_COMMAND;           
            PreparedStatement statement = DataBaseManager.getConnection().prepareStatement(query);
            
            statement.setString(1, course.getName());
            statement.setString(2, course.getNrc());
            statement.setInt(3, course.getIdSection());
            statement.setInt(4, course.getIdBlock());
            statement.setInt(5, course.getIdSchoolPeriod());
            statement.setInt(6, course.getIdEducationalExperience());
            statement.setInt(7, idCourse);
            
            RESULT = statement.executeUpdate();
            modifyProfessorInCourse(idCourse, selectedUsers);
            DataBaseManager.getConnection().commit();
        } catch (SQLException sQLException) {
            DataBaseManager.rollback();            
        } finally {
            DataBaseManager.closeConnection();
        }
        return RESULT;
    }
    
    private void insertUsersInCourse(int idCourse, List<UserTable> selectedUsers) throws SQLException {
        try {
            DataBaseManager.getConnection().setAutoCommit(false);
            String query = ADD_USERS_TO_COURSE_QUERY;
            PreparedStatement statement = DataBaseManager.getConnection().prepareStatement(query);
            for (User user : selectedUsers) {
                statement.setString(1, user.getIdUser());
                statement.setInt(2, idCourse);
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException sQLException) {
            DataBaseManager.rollback();
            throw sQLException;
        }
    }
        
    
    private void modifyProfessorInCourse(int idCourse, List<UserTable> selectedUsers) throws SQLException {
        String idActualProfessor = null;
        
        try {
            idActualProfessor = getProfessorOfCourse(idCourse).getIdUser();
            DataBaseManager.getConnection().setAutoCommit(false);
            String query = MODIFY_PROFESSOR_IN_COURSE_COMMAND;
            PreparedStatement statement = DataBaseManager.getConnection().prepareStatement(query);
            
            for (User user : selectedUsers) {
                if (user.getType() == 0) {
                    statement.setString(1, user.getIdUser());
                    statement.setInt(2, idCourse);
                    statement.setString(3, idActualProfessor);
                    statement.addBatch();
                }
            }
            
            statement.executeBatch();
        } catch (SQLException sQLException) {
            DataBaseManager.rollback();
        }
    }

    @Override
    public Course getCourseById(int idCourse) throws SQLException {
        Course course = new Course();
        
        try {
            DataBaseManager.getConnection();
            String query = GET_COURSE_QUERRY;
            PreparedStatement statement = DataBaseManager.getConnection().prepareStatement(query);
            statement.setInt(1, idCourse);
            ResultSet result = statement.executeQuery();
            
            if (result.next()) {
                course = getCourse(result);
            }   
        } finally {
            DataBaseManager.closeConnection();
        }
        return course;
    }
    
    @Override
    public Course getCourseByNrc(String nrc) throws SQLException {
        Course course = new Course();
        
        try (Connection connection = DataBaseManager.getConnection()) {
            
            String query = GET_COURSE_BY_NRC_QUERY;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nrc);
            ResultSet result = statement.executeQuery();
           
            if (result.next()) {
                course = getCourse(result);
            }
        } finally {
            DataBaseManager.closeConnection();
        }
        return course;
    }
    
    @Override
    public Boolean isCourseRegistered (Course course) throws SQLException {
        boolean flag = false;
        int ACTIVE = 1;
        
        try (Connection connection = DataBaseManager.getConnection()) {
            
            String query = VERIFY_COURSE_REGISTERED_QUERY;
            
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, course.getName());
            statement.setString(2, course.getNrc());
            statement.setInt(3, ACTIVE);
            statement.setInt(4, course.getIdSection());
            statement.setInt(5, course.getIdBlock());
            statement.setInt(6, course.getIdSchoolPeriod());
            statement.setInt(7, course.getIdEducationalExperience());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                flag = true;
            }
        } finally {
            DataBaseManager.closeConnection();
        }
        return flag;
    }
    
    @Override
    public int diseableCourse(int idCourse) throws SQLException {
        int RESULT = 0;
        
        try (Connection connection = DataBaseManager.getConnection()) {
            
            connection.setAutoCommit(false);            
            String query = DISEABLE_COURSE_COMMAND;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCourse);
            RESULT = statement.executeUpdate();
            connection.commit();            
        } catch (SQLException sQLException){
            DataBaseManager.rollback();            
        } finally {
            DataBaseManager.closeConnection();
        }
        return RESULT;  
    }
    
    @Override
    public int getStatusCourse (Course course) throws SQLException {
        int STATUS;
        
        try (Connection connection = DataBaseManager.getConnection()) {
            String query = GET_STATUS_COURSE_QUERY;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, course.getIdCourse());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            STATUS = resultSet.getInt("estado");
        } finally {
            DataBaseManager.closeConnection();
        }
        return STATUS;
    }
    
    @Override
    public Boolean isNrcActive (Course course) throws SQLException {
        boolean active = false;
        
        try (Connection connection = DataBaseManager.getConnection()) {
            
            String query = VERIFY_COURSE_ACTIVE_COMMAND;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, course.getNrc());
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                active = true;
            }
        } finally {
            DataBaseManager.closeConnection();
        }
        return active;
    }
    
    @Override
    public User getProfessorOfCourse(int idCourse) throws SQLException {
        User user = new User();
        
        try (Connection connection = DataBaseManager.getConnection()) {
            
            String query = GET_PROFESSOR_OF_COURSE_QUERY;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCourse);
            ResultSet result = statement.executeQuery();
            
            if (result.next()) {
                user.setIdUser(result.getString("idUsuario"));
                user.setFirstName(result.getString("nombre"));
                user.setMiddleName(result.getString("primerApellido"));
                user.setLastName(result.getString("segundoApellido"));
                user.setInstitutionalEmail(result.getString("correoInstitucional"));
                user.setIdStatus(result.getInt("estado"));
                user.setType(result.getInt("tipoUsuario"));
            }
        } finally {
            DataBaseManager.closeConnection();
        }
        return user; 
    }
    
    @Override
    public List<User> getStudentsOfCourse(int idCourse) throws SQLException {
        List<User> studentsOFCourse = new ArrayList<>();
        
        try (Connection connection = DataBaseManager.getConnection()) {
            String query = GET_STUDENTS_OF_COURSE_QUERY;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCourse);
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                User user = new User();
                user.setIdUser(result.getString("idUsuario"));
                user.setFirstName(result.getString("nombre"));
                user.setMiddleName(result.getString("primerApellido"));
                user.setLastName(result.getString("segundoApellido"));
                user.setInstitutionalEmail(result.getString("correoInstitucional"));
                user.setIdStatus(result.getInt("estado"));
                user.setType(result.getInt("tipoUsuario"));
                studentsOFCourse.add(user);
            }
        } finally {
            DataBaseManager.closeConnection();
        }
        return studentsOFCourse; 
    }
    
    private Course getCourse(ResultSet result) throws SQLException {
        Course course = new Course();
        
        course.setIdCourse(result.getInt("idCurso"));
        course.setName(result.getString("nombre"));
        course.setNrc(result.getString("nrc"));
        course.setStatus(result.getInt("estado"));
        course.setIdSection(result.getInt("idSeccion"));
        course.setIdBlock(result.getInt("idBloque"));
        course.setIdSchoolPeriod(result.getInt("idPeriodoEscolar"));
        course.setIdEducationalExperience(result.getInt("IdExperienciaEducativa"));
        
        return course;
    }
    
    @Override
    public int numberOfActivities(String idUser) throws SQLException {
        int numberOfActivities = 0;
        String query = GET_NUMBER_OF_ACTIVITIES;
        Connection connection = DataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, idUser);
        ResultSet result= statement.executeQuery();
        if(result.next()) {
            numberOfActivities = result.getInt("numberOfActivities");
        }
        return numberOfActivities;
    }
    
    @Override
    public int numberOfActivitiesCompleted(String idUser) throws SQLException {
        int numberOfActivitiesCompleted = 0;
        String query = GET_NUMBER_OF_ACTIVITIES_COMPLETED;
        Connection connection = DataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, idUser);
        ResultSet result= statement.executeQuery();
        if(result.next()) {
            numberOfActivitiesCompleted= result.getInt("numberActivitiesCompleted");
        }
        return numberOfActivitiesCompleted;
    }

    @Override
    public String getReceptionalWorkName(String idUser) throws SQLException {
        String receptionalWorkName = null;
        String query = GET_RECEPCTIONAL_WORK_NAME_BY_STUDENT_QUERY;
        Connection connection = DataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, idUser);
        ResultSet result= statement.executeQuery();
        result.next();
        receptionalWorkName = result.getString("nombre");
        DataBaseManager.closeConnection();
        return receptionalWorkName;
    }
}

