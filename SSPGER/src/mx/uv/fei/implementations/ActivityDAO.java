package mx.uv.fei.implementations;

import mx.uv.fei.contracts.IActivity;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import mx.uv.fei.dataaccess.DataBaseManager;
import mx.uv.fei.logic.Activity;

/**
 *
 * @author Star3oy
 */

public class ActivityDAO implements IActivity{
    
    private final static String ADD_ACTIVITY_QUERY = "insert into actividades (titulo, detalles, fechaInicio,"
                + "fechaFin, estado) values(?, ?, ?, ?, ?)";
    
    private final static String GET_ACTIVITY_QUERY = "SELECT * into actividades WHERE idActividad = ?";

    @Override
    public int addActivity(Activity activity) throws SQLException {
        int result = 0;
        String query = ADD_ACTIVITY_QUERY ;
      
        Connection connection = DataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        
        statement.setString(1, activity.getTitle());
        statement.setString(2, activity.getDetails());
        statement.setDate(3, (Date) activity.getStartDate());
        statement.setDate(4, (Date) activity.getFinishDate());
        statement.setInt(5,activity.getStatus());
        result = statement.executeUpdate();
        if (result == 0) {
            throw new SQLException ("Error al añadir usuario");
        } 
        DataBaseManager.closeConnection();
        return result;     
    }
    
    public Date convertLocalDateToDate(LocalDate date){
            return java.sql.Date.valueOf(date);
    }

    @Override
    public Activity getActivity(int idActivity) throws SQLException {
        String query = GET_ACTIVITY_QUERY;
        Activity activity = new Activity();
        
        Connection connection = DataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idActivity);
        ResultSet result= statement.executeQuery();
        result.next();
        activity.setIdActivity(result.getInt("idActividad"));
        activity.setTitle(result.getString("titulo"));
        activity.setDetails(result.getString("detalles")); 
        activity.setStartDate(result.getDate("fechaInicio"));
        activity.setFinishDate(result.getDate("fechaFina"));
        activity.setStatus(result.getInt("estatus"));
        activity.setIdUsuario(result.getString("idUsuario"));
        
        DataBaseManager.closeConnection();
        return activity;
    }
    
}
