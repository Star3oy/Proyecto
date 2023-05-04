package mx.uv.fei.implementations;

import mx.uv.fei.contracts.ILogin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import mx.uv.fei.dataaccess.DataBaseManager;
import mx.uv.fei.logic.Login;

/**
 *
 * @author Star3oy
 */
public class LoginDAO implements ILogin{

    private final String ADD_LOGIN_QUERY = "insert into login (contraseña, idUsuario) values(?,?)";

    
    @Override
    public int addLogin(Login login) throws SQLException {
        int result;
        String query = ADD_LOGIN_QUERY;
        
        Connection connection = DataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        
        statement.setString(1, login.getPassword());
        statement.setString(2, login.getIdUser());
    
        result = statement.executeUpdate();
        if (result == 0) {
            throw new SQLException ("Error al añadir usuario");
        } 
        DataBaseManager.closeConnection();
        return result;    
    }
    

    
}
