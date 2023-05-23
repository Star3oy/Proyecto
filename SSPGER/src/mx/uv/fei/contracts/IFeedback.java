package mx.uv.fei.contracts;

import mx.uv.fei.logic.Feedback;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Star3oy
 */
public interface IFeedback {
    public int addFeedback(Feedback feedback) throws SQLException;
    public List<Feedback> getFeedbackByUser(String idUser)throws SQLException;
    public List<Feedback> getAllFeedBackByProgress(int idAvance)throws SQLException;
}
