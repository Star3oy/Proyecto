package mx.uv.fei.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.uv.fei.dataaccess.DataBaseManager;
import mx.uv.fei.logic.UserTable;

/**
 *
 * @author sue
 */
public class UserTableDAO {
    private final String GET_STUDENTS_NOT_SELECTED_QUUERY = "SELECT * FROM usuarios WHERE tipoUsuario = ? AND estado = ? "
            + "AND idUsuario NOT IN (SELECT uc.idUsuario FROM usuarios_cursos uc INNER JOIN cursos c ON uc.idCurso = c.idCurso "
            + "WHERE c.nombre = ? AND c.estado = ?)";
    
    public List<UserTable> getStudentsForCourse(String educationalExperience) throws SQLException {
        List<UserTable> userTables = new ArrayList<>();
        int STUDENT_TYPE = 1;
        int ACTIVE_STUDENT = 1;
        int ACTIVE_COURSE = 1;
        
        try (Connection connection = DataBaseManager.getConnection()) {
            String query = GET_STUDENTS_NOT_SELECTED_QUUERY;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, STUDENT_TYPE);
            statement.setInt(2, ACTIVE_STUDENT);
            statement.setString(3, educationalExperience);
            statement.setInt(4, ACTIVE_COURSE);
            ResultSet result = statement.executeQuery();
            
            while(result.next()) {
                UserTable userTable = new UserTable();
                userTable.setIdUser(result.getString("idUsuario"));
                userTable.setFirstName(result.getString("nombre"));
                userTable.setMiddleName(result.getString("primerApellido"));
                userTable.setLastName(result.getString("segundoApellido"));
                userTable.setType(result.getInt("tipoUsuario"));
                userTables.add(userTable);
            }
        } finally {
            DataBaseManager.closeConnection();
        }
        return userTables;  
    }
}
