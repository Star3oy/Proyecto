package mx.uv.fei.contracts;

import java.sql.SQLException;
import java.util.List;
import mx.uv.fei.logic.DeliverableFile;
import mx.uv.fei.logic.Progress;

/**
 *
 * @author sue
 */
public interface IProgress {
        String getStudentComment(int idProgress) throws SQLException;
    List<Progress> getAllProgressesByActivity(int idActivity) throws SQLException;
    List<Progress> getProgressesByStatus(int idActivity, int status) throws SQLException;
    int sendProgressTransaction(Progress progress, DeliverableFile file) throws SQLException;
}
