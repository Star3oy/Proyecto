package mx.uv.fei.implementations;

import mx.uv.fei.contracts.IFeedback;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import mx.uv.fei.dataaccess.DataBaseManager;
import mx.uv.fei.logic.Feedback;

/**
 *
 * @author Star3oy
 */
public class FeedbackDAO implements IFeedback{

    private final String ADD_FEEDBACK_COMMAND = "insert into retroalimentaciones (comentario, fechaRetroalimentacion) values(?, ?)";
    
    @Override
    public int addFeedback(Feedback feedback) throws SQLException {
        int result;
        String query = ADD_FEEDBACK_COMMAND;
        
        Connection connection = DataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, feedback.getComent());
         statement.setDate(2, (Date) feedback.getFeedBackDate());

        result = statement.executeUpdate();
        if (result == 0) {
            throw new SQLException ("Error al crear la retroalimentación");
        } 
        DataBaseManager.closeConnection();
        return result;  
    }
    
}
