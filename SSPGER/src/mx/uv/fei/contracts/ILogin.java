package mx.uv.fei.contracts;

import java.sql.SQLException;
import mx.uv.fei.logic.Login;


/**
 *
 * @author Star3oy
 */
public interface ILogin {
    int addLogin (Login login) throws SQLException;
}
