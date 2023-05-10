
package mx.uv.fei.contracts;

import java.sql.SQLException;
import java.util.List;
import mx.uv.fei.logic.User;


public interface IUser {
    int addUser (User user) throws SQLException;
    List<User> getUserList() throws SQLException;
    User getUser (String idUser)throws SQLException;
    int modifyUser (User user, String idUser) throws SQLException;
    int disableUser (String idUser) throws SQLException;
    int verifyUserExistence (User user) throws SQLException;
    int verifyUserEmail(String email) throws SQLException;
    List<User> getUsersByStatus(int status) throws SQLException;
}
