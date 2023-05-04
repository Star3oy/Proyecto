package mx.uv.fei.contracts;

import mx.uv.fei.logic.User;
import java.sql.SQLException;
import mx.uv.fei.logic.Login;
/**
 *
 * @author Star3oy
 */
public interface IUserManager {
    public int userAdditionTransition (User userAccount, Login login) throws SQLException;
}
