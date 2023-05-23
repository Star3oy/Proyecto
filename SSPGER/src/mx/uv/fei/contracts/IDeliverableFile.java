package mx.uv.fei.contracts;

import java.sql.SQLException;
import mx.uv.fei.logic.DeliverableFile;


/**
 *
 * @author Star3oy
 */
public interface IDeliverableFile {
    int addDeliverableFile (DeliverableFile deliverableFile) throws SQLException;
    DeliverableFile getDeliverableFile (String deliverableFileName)throws SQLException;
    DeliverableFile getDeliverableFileByProgress (int idProgress) throws SQLException;
    
}
