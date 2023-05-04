package mx.uv.fei.bussinesslogic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
    
}
