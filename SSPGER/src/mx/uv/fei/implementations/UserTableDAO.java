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
    public List<UserTable> getStudents() throws SQLException {
        List<UserTable> userTables = new ArrayList<>();
        
        try (Connection connection = DataBaseManager.getConnection()) {
            String query = "SELECT * FROM usuarios WHERE idRol = 4 AND estado = 1";
            PreparedStatement statement = connection.prepareStatement(query);
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
        } catch (SQLException sQLException){
            throw sQLException;
        } finally {
            DataBaseManager.closeConnection();
        }
        return userTables;  
    }
}
