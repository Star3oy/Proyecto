package mx.uv.fei.implementations;

import mx.uv.fei.contracts.IUserManager;
import java.sql.SQLException;
import mx.uv.fei.dataaccess.DataBaseManager;
import mx.uv.fei.logic.User;
import java.sql.PreparedStatement;
import mx.uv.fei.logic.Login;

/**
 *
 * @author Star3oy
 */
public class UserManagerDAO implements IUserManager{
        
    private final String ADD_USER_COMMAND = "insert into usuarios(idUsuario, nombre, primerApellido, segundoApellido,"
                + "correoInstitucional, estado, idRol) values(?, ?, ?, ?, ?, ?, ?)";

    private final String ADD_LOGIN_COMMAND = "insert into login (contraseña, idUsuario) values(sha1(?),?)";
    
    @Override
    public int userAdditionTransition(User user, Login login) throws SQLException {
        int response = -1;
        
        try {
        DataBaseManager.getConnection().setAutoCommit(false);
 
         PreparedStatement userStatement = DataBaseManager.getConnection().prepareStatement(ADD_USER_COMMAND);
        userStatement.setString(1, user.getIdUser());
        userStatement.setString(2, user.getFirstName());
        userStatement.setString(3, user.getMiddleName());
        userStatement.setString(4,user.getLastName());
        userStatement.setString(5,user.getInstitutionalEmail());
        userStatement.setInt(6, user.getIdStatus());
        userStatement.setInt(7, user.getType());
        
        response =  userStatement.executeUpdate();
        
        PreparedStatement loginStatement= DataBaseManager.getConnection().prepareStatement(ADD_LOGIN_COMMAND);
       
        loginStatement.setString(1, login.getPassword());
        loginStatement.setString(2, login.getIdUser());
        response = response + loginStatement.executeUpdate();
        
        DataBaseManager.getConnection().commit();
   
        } catch (SQLException ex) {
            DataBaseManager.getConnection().rollback();
        } finally {
            DataBaseManager.getConnection().close();
        }
        return response;
    }
    
}
