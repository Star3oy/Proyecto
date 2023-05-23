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

    @Override
    public int registerCourse (Course course, List<UserTable> selectedUsers) throws SQLException {
        int result;
        int ACTIVE = 1;
        ResultSet generatedKeys = null;
        
        try {
            DataBaseManager.getConnection().setAutoCommit(false);
            String query = "INSERT INTO cursos(nombre, nrc, estado, idSeccion, "
                + "idBloque, idPeriodoEscolar, idExperienciaEducativa) VALUES(?,?,?,?,?,?,?)";
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
    
    private void insertUsersInCourse(int idCourse, List<UserTable> selectedUsers) throws SQLException {
        try {
            DataBaseManager.getConnection().setAutoCommit(false);
            String query = "INSERT INTO usuarios_cursos(idUsuario, idCurso, tipoUsuario) VALUES(?,?,?)";
            PreparedStatement statement = DataBaseManager.getConnection().prepareStatement(query);
            for (User user : selectedUsers) {
                statement.setString(1, user.getIdUser());
                statement.setInt(2, idCourse);
                statement.setInt(3, user.getType());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException sQLException) {
            DataBaseManager.rollback();
            throw sQLException;
        }
    }

    @Override
    public List<Course> getAllCourses() throws SQLException {
        List<Course> courseList = new ArrayList<>();
        
        try (Connection connection = DataBaseManager.getConnection()) {
            String query = "SELECT * FROM cursos";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                Course course = new Course();
                course.setIdCourse(result.getInt("idCurso"));
                course.setName(result.getString("nombre"));
                course.setNrc(result.getString("nrc"));
                course.setStatus(result.getInt("estado"));
                course.setIdSection(result.getInt("idSeccion"));
                course.setIdBlock(result.getInt("idbloque"));
                course.setIdSchoolPeriod(result.getInt("idPeriodoEscolar"));
                course.setIdEducationalExperience(result.getInt("IdExperienciaEducativa"));
                courseList.add(course);
            }
        } catch (SQLException sQLException) {
            throw sQLException;
        } finally {
            DataBaseManager.closeConnection();
        }
        return courseList; 
    }

    @Override
    public Course getCourseById(int idCourse) throws SQLException {
        Course course = new Course();
        
        try (Connection connection = DataBaseManager.getConnection()) {
            String query = "SELECT * FROM cursos WHERE idCurso = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCourse);
            ResultSet result = statement.executeQuery();
            result.next();
            course.setIdCourse(result.getInt("idCurso"));
            course.setName(result.getString("nombre"));
            course.setNrc(result.getString("nrc"));
            course.setStatus(result.getInt("estado"));
            course.setIdSection(result.getInt("idSeccion"));
            course.setIdBlock(result.getInt("idBloque"));
            course.setIdSchoolPeriod(result.getInt("idPeriodoEscolar"));
            course.setIdEducationalExperience(result.getInt("IdExperienciaEducativa"));
        } catch (SQLException sQLException) {
            throw sQLException;
        } finally {
            DataBaseManager.closeConnection();
        }
        return course;
    }
    
    @Override
    public Course getCourseByNrc(String nrc) throws SQLException {
        Course course = new Course();
        
        try (Connection connection = DataBaseManager.getConnection()) {
            String query = "SELECT * FROM cursos WHERE nrc = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nrc);
            ResultSet result = statement.executeQuery();
            result.next();
            course.setIdCourse(result.getInt("idCurso"));
            course.setName(result.getString("nombre"));
            course.setNrc(result.getString("nrc"));
            course.setStatus(result.getInt("estado"));
            course.setIdSection(result.getInt("idSeccion"));
            course.setIdBlock(result.getInt("idBloque"));
            course.setIdSchoolPeriod(result.getInt("idPeriodoEscolar"));
            course.setIdEducationalExperience(result.getInt("IdExperienciaEducativa"));
        } catch (SQLException sQLException) {
            throw sQLException;
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
            String query = "SELECT * FROM cursos WHERE (nombre = ? AND nrc = ?) AND (estado = ? AND idSeccion = ?)"
                + "AND (idBloque = ? AND idPeriodoEscolar = ?) AND idExperienciaEducativa = ?";
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
        } catch (SQLException sQLException) {
            throw sQLException;
        } finally {
            DataBaseManager.closeConnection();
        }
        return flag;
    }
    
    @Override
    public int diseableCourse(int idCourse) throws SQLException {
        int result;
        
        try (Connection connection = DataBaseManager.getConnection()) {
            connection.setAutoCommit(false);            
            String query = "UPDATE cursos SET estado = 0 WHERE idCurso = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCourse);
            result = statement.executeUpdate();
            connection.commit();            
            if (result == 0) {
                throw new SQLException("Error al desactivar curso");                
            }
        } catch (SQLException sQLException){
            DataBaseManager.rollback();            
            throw sQLException;
        } finally {
            DataBaseManager.closeConnection();
        }
        return result;  
    }
    
    @Override
    public int modifyCourse(Course course) throws SQLException {
        int result;
        
        try (Connection connection = DataBaseManager.getConnection()) {
            String query = "UPDATE cursos SET nombre = ?, nrc = ?, idSeccion = ?,"
                   + "idBloque = ?, idPeriodoEscolar = ?, idExperienciaEducativa = ? "
                   + "WHERE idCurso = ?";           
            connection.setAutoCommit(false);                        
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, course.getName());
            statement.setString(2, course.getNrc());
            statement.setInt(3, course.getIdSection());
            statement.setInt(4, course.getIdBlock());
            statement.setInt(5, course.getIdSchoolPeriod());
            statement.setInt(6, course.getIdEducationalExperience());
            statement.setInt(7, course.getIdCourse());
            result = statement.executeUpdate();
            connection.commit();                        
            if (result == 0) {
                 throw new SQLException("Error al modificar curso");               
            }
        } catch (SQLException sQLException) {
            DataBaseManager.rollback();            
            throw sQLException;
        } finally {
            DataBaseManager.closeConnection();
        }
        return result;
    }
    
    @Override
    public int courseActive (Course course) throws SQLException {
        int result;
        
        try (Connection connection = DataBaseManager.getConnection()) {
            String query = "SELECT estado FROM cursos WHERE idCurso = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, course.getIdCourse());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            result = resultSet.getInt("estado");
        } catch (SQLException sQLException) {
            throw sQLException;
        } finally {
            DataBaseManager.closeConnection();
        }
        return result;
    }
    
    @Override
    public List<Course> getCoursesByStatus(int status) throws SQLException {
        List<Course> courseList = new ArrayList<>();
        
        try (Connection connection = DataBaseManager.getConnection()) {
            String query = "SELECT * FROM cursos WHERE estado = ?";
            PreparedStatement statement = connection.prepareStatement(query);            
            statement.setInt(1, status);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Course course = new Course();
                course.setIdCourse(result.getInt("idCurso"));
                course.setName(result.getString("nombre"));
                course.setNrc(result.getString("nrc"));
                course.setStatus(result.getInt("estado"));
                course.setIdSection(result.getInt("idSeccion"));
                course.setIdBlock(result.getInt("idbloque"));
                course.setIdSchoolPeriod(result.getInt("idPeriodoEscolar"));
                course.setIdEducationalExperience(result.getInt("IdExperienciaEducativa"));
                courseList.add(course);
            }
        } catch (SQLException sQLException) {
            throw sQLException;
        } finally {
            DataBaseManager.closeConnection();
        }
        return courseList; 
    }
    
    @Override
    public Boolean isNrcActive (Course course) throws SQLException {
        boolean flag = false;
        
        try (Connection connection = DataBaseManager.getConnection()) {
            String query = "SELECT * FROM cursos WHERE nrc = ? AND estado = 1";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, course.getNrc());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                flag = true;
            }
        } catch (SQLException sQLException) {
            throw sQLException;
        } finally {
            DataBaseManager.closeConnection();
        }
        return flag;
    }
    
    @Override
    public User getProfessorOfCourse(int idCourse) throws SQLException {
        User user = new User();
        
        try (Connection connection = DataBaseManager.getConnection()) {
            String query = "SELECT usuarios.* FROM usuarios JOIN usuarios_cursos "
                    + "ON usuarios.idUsuario = usuarios_cursos.idUsuario "
                    + "JOIN cursos ON usuarios_cursos.IdCurso = cursos.IdCurso "
                    + "WHERE cursos.IdCurso = ? AND usuarios.idRol = 2";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCourse);
            ResultSet result = statement.executeQuery();
            result.next();
            user.setIdUser(result.getString("idUsuario"));
            user.setFirstName(result.getString("nombre"));
            user.setMiddleName(result.getString("primerApellido"));
            user.setLastName(result.getString("segundoApellido"));
            user.setInstitutionalEmail(result.getString("correoInstitucional"));
            user.setIdStatus(result.getInt("estado"));
            user.setType(result.getInt("tipoUsuario"));
        } catch (SQLException sQLException) {
            throw sQLException;
        } finally {
            DataBaseManager.closeConnection();
        }
        return user; 
    }
    
    @Override
    public List<User> getStudentsOfCourse(int idCourse) throws SQLException {
        List<User> studentsOFCourse = new ArrayList<>();
        
        try (Connection connection = DataBaseManager.getConnection()) {
            String query = "SELECT usuarios.* FROM usuarios JOIN usuarios_cursos "
                    + "ON usuarios.idUsuario = usuarios_cursos.idUsuario "
                    + "JOIN cursos ON usuarios_cursos.IdCurso = cursos.IdCurso "
                    + "WHERE cursos.IdCurso = ? AND usuarios.idRol = 4";
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
                user.setType(result.getInt("idRol"));
                studentsOFCourse.add(user);
            }
        } catch (SQLException sQLException) {
            throw sQLException;
        } finally {
            DataBaseManager.closeConnection();
        }
        return studentsOFCourse; 
    }
}

