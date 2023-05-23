package mx.uv.fei.contracts;

import mx.uv.fei.logic.ReceptionalWork;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author cris9
 */
public interface IReceptionalWork {
    int createReceptionalWork(ReceptionalWork receptionalWork) throws SQLException;
    List<ReceptionalWork> getReceptionalJobsByDirector(String idDirector) throws SQLException;
    int concludeReceptionalWork(ReceptionalWork receptionalWork, int idReceptionalWork) throws SQLException;
    ReceptionalWork getReceptionalWork(int idReceptionalWork) throws SQLException;
    int addSynodal(String idSynodal, int idReceptionalWork) throws SQLException;
    List<ReceptionalWork> getCurrentReceptionalJobsByDirector(String idDirector) throws SQLException;
    String getReceptionalWorkName (String idUser) throws SQLException;
}
