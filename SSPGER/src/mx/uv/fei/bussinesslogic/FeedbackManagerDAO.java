package mx.uv.fei.bussinesslogic;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import mx.uv.fei.dataaccess.DataBaseManager;
import mx.uv.fei.logic.DeliverableFile;
import mx.uv.fei.logic.Feedback;

/**
 *
 * @author Star3oy
 */
public class FeedbackManagerDAO implements IFeedbackManager{
    
    private final String ADD_FEEDBACK_COMMAND = "insert into retroalimentaciones (comentario, fechaRetroalimentacion) values(?, ?)";
    private final String ADD_DELIVERABLEFILE_COMMAND = "insert into entregables(tipo, nombre, ruta) values(?, ?, ?)";

    @Override
    public int feedbackAdditionTransition(Feedback feedback, List<DeliverableFile> fileList) throws SQLException {
        int response = -1;     
        try {
        DataBaseManager.getConnection().setAutoCommit(false); 
         PreparedStatement feedbackStatement = DataBaseManager.getConnection().prepareStatement(ADD_FEEDBACK_COMMAND);
         
         feedbackStatement.setString(1, feedback.getComent());
         feedbackStatement.setDate(2, (Date) feedback.getFeedBackDate());
         response =  feedbackStatement.executeUpdate();  
       
        for (int i = 0; i < fileList.size(); i++) {
        PreparedStatement fileStatement= DataBaseManager.getConnection().prepareStatement(ADD_DELIVERABLEFILE_COMMAND);
        fileStatement.setString(1,fileList.get(i).getType() );
        fileStatement.setString(2,fileList.get(i).getName());
        fileStatement.setString(3,fileList.get(i).getPathName());
        response = response + fileStatement.executeUpdate();    
        }
        
        DataBaseManager.getConnection().commit();
        } catch (SQLException ex) {
            DataBaseManager.getConnection().rollback();
        } finally {
            DataBaseManager.getConnection().close();
        }
        return response;
    }
    
}
