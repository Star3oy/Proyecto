package mx.uv.fei.contracts;

import mx.uv.fei.logic.Activity;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Star3oy
 */

public interface IActivity {
    public int addActivity (Activity activity) throws SQLException;
    public Activity getActivity(String title) throws SQLException;
    public List<Activity> getActivityList() throws SQLException;
    public List<Activity> getActivityListByStatus(int status) throws SQLException;
}
