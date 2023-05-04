package mx.uv.fei.implementations;

import mx.uv.fei.contracts.IDeliverableFile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mx.uv.fei.dataaccess.DataBaseManager;
import mx.uv.fei.logic.DeliverableFile;


/**
 *
 * @author Star3oy
 */
public class DeliverableFileDAO implements IDeliverableFile{

        private final String ADD_DELIVERABLEFILE_COMMAND = "insert into entregables(tipo, nombre, ruta) values(?, ?, ?)";
        private final String GET_DELIVERABLEFILE_QUERY = "SELECT * FROM entregalbles WHERE idEntregable = ?";
    
    @Override
    public int addDeliverableFile (DeliverableFile deliverableFile) throws SQLException {
        int result;
        String query = ADD_DELIVERABLEFILE_COMMAND;
        Connection connection = DataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        
        statement.setString(1,deliverableFile.getType() );
        statement.setString(2,deliverableFile.getName());
        statement.setString(3,deliverableFile.getPathName());
    //    statement.setInt(4,deliverableFile.getIdProgress());
     //   statement.setInt(5,deliverableFile.getIdProgress());
        result = statement.executeUpdate();
        
        if (result == 0) {
            throw new SQLException ("Error al agregar documento");
        } 
        DataBaseManager.closeConnection();
        return result;  
    }

    @Override
    public DeliverableFile getUser(String idDeliverableFile) throws SQLException {
              DeliverableFile deliverableFile = new DeliverableFile();
        String query = GET_DELIVERABLEFILE_QUERY;
        
    Connection connection = DataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, idDeliverableFile);
        ResultSet result= statement.executeQuery();
        result.next();
        deliverableFile.setName(result.getString("nombre"));
        deliverableFile.setType(result.getString("tipo"));          
        deliverableFile.setPathName(result.getString("ruta"));
        
        DataBaseManager.closeConnection();
        return deliverableFile;
    }
    
}
