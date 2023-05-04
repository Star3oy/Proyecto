package mx.uv.fei.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mx.uv.fei.dataaccess.DataBaseManager;
import mx.uv.fei.logic.User;

/**
 *
 * @author Star3oy
 */

public class UserDAO implements IUser{
    
    
    private final String ADD_USER_COMMAND = "insert into usuarios(idUsuario, nombre, primerApellido, segundoApellido,"
                + "correoInstitucional, estado, idRol) values(?, ?, ?, ?, ?, ?, ?)";
    private final String GET_ALL_USERS_QUERY = "SELECT * FROM usuarios";
    private final String GET_USER_QUERY = "SELECT * FROM usuarios WHERE idUsuario = ?";
    private final String MODIFY_USER_COMMAND = "UPDATE usuarios SET idUsuario = ?, nombre = ?, primerApellido = ?, segundoApellido = ?,"
                + "correoInstitucional = ?, estado = ?, idRol = ? WHERE idUsuario = ?";
    private final String DISABLE_USER_COMMAND = "UPDATE usuarios SET estado = 0 WHERE idUsuario = ?";
    private final String GET_ACTIVES_USERS_QUERY = "SELECT * FROM usuarios WHERE estado = 1";
    private final String GET_INACTIVES_USERS_QUERY = "SELECT * FROM usuarios WHERE estado = 0";
    
    
    @Override
    public int addUser (User user) throws SQLException {
        int result;
        String query = ADD_USER_COMMAND;
        
        Connection connection = DataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getIdUser());
        statement.setString(2, user.getFirstName());
        statement.setString(3, user.getMiddleName());
        statement.setString(4,user.getLastName());
        statement.setString(5,user.getInstitutionalEmail());
        statement.setInt(6, user.getIdStatus());
        statement.setInt(7, user.getIdRole());
        result = statement.executeUpdate();
        if (result == 0) {
            throw new SQLException ("Error al añadir usuario");
        } 
        DataBaseManager.closeConnection();
        return result;       
    }
        
        
    @Override
    public List<User> getUserList() throws SQLException {
        List<User> userList = new ArrayList<>();
        
        String query = GET_ALL_USERS_QUERY;
        
       Connection connection = DataBaseManager.getConnection(); 
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);              
            while(result.next()) {
                User user = new User();
                user.setIdUser(result.getString("idUsuario"));
                user.setFirstName(result.getString("nombre"));
                user.setMiddleName(result.getString("primerApellido"));
                user.setLastName(result.getString("segundoApellido"));
                user.setInstitutionalEmail(result.getString("correoInstitucional"));
                user.setIdStatus(result.getInt("estado"));
                user.setIdRole(result.getInt("idRol"));
                userList.add(user);
            }
        DataBaseManager.closeConnection();
        
        return userList;    
    }

    @Override
    public User getUser(String idUser) throws SQLException {
        User user = new User();
        String query = GET_USER_QUERY;
        
    Connection connection = DataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, idUser);
        ResultSet result= statement.executeQuery();
        result.next();
        user.setIdUser(result.getString("idUsuario"));
        user.setFirstName(result.getString("nombre"));                
        user.setMiddleName(result.getString("primerApellido"));
        user.setLastName(result.getString("segundoApellido"));
        user.setInstitutionalEmail(result.getString("correoInstitucional"));
        user.setIdStatus(result.getInt("estado"));
        user.setIdRole(result.getInt("idRol"));
        
        DataBaseManager.closeConnection();
        return user;
    }

    @Override 
    public int modifyUser(User user, String idUser) throws SQLException {
      int result = 0;
     String query = MODIFY_USER_COMMAND;

      Connection connection = DataBaseManager.getConnection();
         PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getIdUser());
        statement.setString(2, user.getFirstName());
        statement.setString(3, user.getMiddleName());
        statement.setString(4, user.getLastName());
        statement.setString(5, user.getInstitutionalEmail());
        statement.setInt(6, user.getIdStatus());
        statement.setInt(7, user.getIdRole());
        statement.setString(8, idUser);
        result = statement.executeUpdate();
         if (result == 0) {
            throw new SQLException ("Error al modificar usuario");
        }

         DataBaseManager.closeConnection();
     
     return result;
   }

    @Override
    public int disableUser(String idUser) throws SQLException {
     int result = 0;
     String query =  DISABLE_USER_COMMAND;
     Connection connection = DataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, idUser);
        result = statement.executeUpdate();
        if (result == 0) {
            throw new SQLException ("Error al desactivar usuario");
        }

      DataBaseManager.closeConnection();
     return result;
  }

    @Override
    public int verifyUserExistence(User user) throws SQLException {
      int cont = 0;
      String query = "SELECT COUNT(*) FROM usuarios WHERE (idusuario = ? AND nombre = ?) AND"
              +"(primerApellido = ? AND segundoApellido = ?)";
      Connection connection = DataBaseManager.getConnection();
          
        PreparedStatement statement = connection.prepareStatement(query);
        
        statement.setString(1, user.getIdUser());
        statement.setString(2, user.getFirstName());
        statement.setString(3, user.getMiddleName());
        statement.setString(4, user.getLastName());
        
        ResultSet resulsetUserExistence = statement.executeQuery();
        resulsetUserExistence.next();
        cont = resulsetUserExistence.getInt(1);
        
        DataBaseManager.closeConnection();
      return cont;
    }

    @Override
    public int verifyUserEmail(String email) throws SQLException {
      int cont = 0;
      String query = "SELECT COUNT(*) FROM usuarios WHERE correoInstitucional = ?";
      Connection connection = DataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, email);
        ResultSet resulsetUserExistence = statement.executeQuery();
        resulsetUserExistence.next();
        cont = resulsetUserExistence.getInt(1); 
        DataBaseManager.closeConnection();
      return cont;
    }

    @Override
    public List<User> getActiveUsers() throws SQLException {
         List<User> userList = new ArrayList<>();
        
        String query = GET_ACTIVES_USERS_QUERY;
        
      Connection connection = DataBaseManager.getConnection(); 
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);
                    
            while(result.next()) {
                User user = new User();
                user.setIdUser(result.getString("idUsuario"));
                user.setFirstName(result.getString("nombre"));
                user.setMiddleName(result.getString("primerApellido"));
                user.setLastName(result.getString("segundoApellido"));
                user.setInstitutionalEmail(result.getString("correoInstitucional"));
                user.setIdStatus(result.getInt("estado"));
                user.setIdRole(result.getInt("idRol"));
                userList.add(user);
            }        
         DataBaseManager.closeConnection();
        return userList;
    }

    @Override
    public List<User> getInactiveUsers() throws SQLException {
 List<User> userList = new ArrayList<>();
        
        String query = GET_INACTIVES_USERS_QUERY;
        
        Connection connection = DataBaseManager.getConnection(); 
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);
                    
            while(result.next()) {
                User user = new User();
                user.setIdUser(result.getString("idUsuario"));
                user.setFirstName(result.getString("nombre"));
                user.setMiddleName(result.getString("primerApellido"));
                user.setLastName(result.getString("segundoApellido"));
                user.setInstitutionalEmail(result.getString("correoInstitucional"));
                user.setIdStatus(result.getInt("estado"));
                user.setIdRole(result.getInt("idRol"));
                userList.add(user);
            }
         DataBaseManager.closeConnection();
        return userList;    
    }
  
}
