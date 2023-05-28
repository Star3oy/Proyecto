package mx.uv.fei.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mx.uv.fei.dataaccess.DataBaseManager;
import mx.uv.fei.logic.Course;
import mx.uv.fei.logic.CourseTable;

/**
 *
 * @author sue 
 */
public class CourseTableDAO {
    public List<CourseTable> getCoursesByStatus(int status) throws SQLException {
        List<CourseTable> courseList = new ArrayList<>();
        
        try (Connection connection = DataBaseManager.getConnection()) {
            String query = "SELECT cursos.nombre, cursos.nrc, cursos.idCurso, periodos_escolares.nombrePeriodo"
                    + " FROM cursos INNER JOIN periodos_escolares on cursos.idPeriodoEscolar = periodos_escolares.idPeriodoEscolar"
                    + " WHERE estado = ?";
            PreparedStatement statement = connection.prepareStatement(query);            
            statement.setInt(1, status);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                CourseTable courseTable = new CourseTable();
                courseTable.setName(result.getString("nombre"));
                courseTable.setNrc(result.getString("nrc"));
                courseTable.setNamePeriod(result.getString("nombrePeriodo"));
                courseList.add(courseTable);
            }
        } catch (SQLException sQLException) {
            throw sQLException;
        } finally {
            DataBaseManager.closeConnection();
        }
        return courseList; 
    }
    
    public List<CourseTable> getAllCourses() throws SQLException {
        List<CourseTable> courseList = new ArrayList<>();
        
        try (Connection connection = DataBaseManager.getConnection()) {
            String query = "SELECT cursos.nombre, cursos.idCurso, cursos.nrc, periodos_escolares.nombrePeriodo"
                    + " FROM cursos INNER JOIN periodos_escolares on cursos.idPeriodoEscolar = periodos_escolares.idPeriodoEscolar";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                CourseTable courseTable = new CourseTable();
                courseTable.setIdCourse(result.getInt("idCurso"));
                courseTable.setName(result.getString("nombre"));
                courseTable.setNrc(result.getString("nrc"));
                courseTable.setNamePeriod(result.getString("nombrePeriodo"));
                courseList.add(courseTable);
            }
        } catch (SQLException sQLException) {
            throw sQLException;
        } finally {
            DataBaseManager.closeConnection();
        }
        return courseList; 
    }
}
