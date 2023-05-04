package mx.uv.fei.contracts;

import mx.uv.fei.logic.Activity;
import java.sql.SQLException;
/**
 *
 * @author Star3oy
 */

public interface IActivity {
    public int addActivity (Activity activity) throws SQLException;
    public Activity getActivity(int idActivity) throws SQLException;
}
