package mx.uv.fei.bussinesslogic;

import mx.uv.fei.logic.Activity;
import java.sql.SQLException;
/**
 *
 * @author Star3oy
 */

public interface IActivity {
    public int addActivity (Activity activity) throws SQLException;
}
