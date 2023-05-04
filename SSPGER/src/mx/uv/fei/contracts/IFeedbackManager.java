package mx.uv.fei.contracts;

/**
 *
 * @author Star3oy
 */

import java.sql.SQLException;
import java.util.List;
import mx.uv.fei.logic.DeliverableFile;
import mx.uv.fei.logic.Feedback;


public interface IFeedbackManager {
    public int feedbackAdditionTransition (Feedback feedback, List<DeliverableFile> fileList) throws SQLException;
}
