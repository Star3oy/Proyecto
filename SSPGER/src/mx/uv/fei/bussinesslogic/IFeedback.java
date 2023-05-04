package mx.uv.fei.bussinesslogic;

import mx.uv.fei.logic.Feedback;
import java.sql.SQLException;
/**
 *
 * @author Star3oy
 */
public interface IFeedback {
    public int addFeedback(Feedback feedback) throws SQLException;
}
