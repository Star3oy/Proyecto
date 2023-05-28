package mx.uv.fei.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mx.uv.fei.contracts.IReceptionalWork;
import mx.uv.fei.dataaccess.DataBaseManager;
import mx.uv.fei.logic.ReceptionalWork;

/**
 *
 * @author cris9
 */
public class ReceptionalWorkDAO implements IReceptionalWork{
    private static final int CURRENT = 1;
    private final String CREATE_RECEPTIONAL_WORK_QUERY = "insert into trabajos_recepcionales(idTrabajoRecepcional, idTesista, "
                                            + "idAnteproyecto, idDirector, idCoDirector, descripcion, idEstadoTrabajo, idModalidad, "
                                                + "nombre) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String GET_RECEPTIONAL_JOBS_QUERY = "select * from trabajos_recepcionales where idDirector = ?";
    private final String GET_CURRENT_RECEPTIONAL_JOBS_QUERY = "select * from trabajos_recepcionales where idDirector = ? and idEstadoTrabajo = ?";
    private final String CONCLUDE_RECEPTIONAL_WORK_QUERY = "update trabajos_recepcionales set idCoDirector = ?, descripcion = ?, "
                                            + "duracionFinal = ?, idEstadoTrabajo = ?, idModalidad = ?, nombre = ?, resultadosObtenidos = ? "
                                                + "where idTrabajoRecepcional = ?";
    private final String GET_RECEPTIONAL_WORK_QUERY = "select * from trabajos_recepcionales where idTrabajoRecepcional = ?";
    private final String ADD_SYNODAL_QUERY = "update trabajos_recepcionales set idSinodal = ? where idTrabajoRecepcional = ?";
    private final String GET_RECEPCTIONAL_WORK_NAME_BY_STUDENT_QUERY = "select nombre from trabajos_recepcionales where idTesista = ?";
    
    @Override
    public int createReceptionalWork(ReceptionalWork receptionalWork) throws SQLException {
        int result;
        String query = CREATE_RECEPTIONAL_WORK_QUERY;
        
        try (Connection connection = DataBaseManager.getConnection()) {  
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, receptionalWork.getIdReceptionalWork());
            statement.setString(2, receptionalWork.getIdStudent());
            statement.setInt(3, receptionalWork.getIdDraft());
            statement.setString(4,receptionalWork.getIdDirector());
            statement.setString(5,receptionalWork.getIdCoDirector());
            statement.setString(6, receptionalWork.getDescription());
            statement.setInt(7, receptionalWork.getIdWorkStatus());
            statement.setInt(8, receptionalWork.getIdModality());
            statement.setString(9, receptionalWork.getWorkName());
            result = statement.executeUpdate();
            if (result == 0){
                throw new SQLException ("Error al crear trabajo recepcional");
            } 
        } catch (SQLException sQLException){
             throw sQLException;
        } finally {
             DataBaseManager.closeConnection();
        }
           return result;
    }

    @Override
    public List<ReceptionalWork> getReceptionalJobsByDirector(String idDirector) throws SQLException {
        String query = GET_RECEPTIONAL_JOBS_QUERY;
        List<ReceptionalWork> jobsList = new ArrayList<>();
        try (Connection connection = DataBaseManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, idDirector);
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                ReceptionalWork receptionalWork = new ReceptionalWork();
                receptionalWork.setIdReceptionalWork(result.getInt("idTrabajoRecepcional"));
                receptionalWork.setIdStudent(result.getString("idTesista"));
                receptionalWork.setIdDraft(result.getInt("idAnteproyecto"));
                receptionalWork.setIdDirector(result.getString("idDirector"));
                receptionalWork.setIdCoDirector(result.getString("idCoDirector"));
                receptionalWork.setDescription(result.getString("descripcion"));
                receptionalWork.setFinalDuration(result.getString("duracionFinal"));
                receptionalWork.setIdWorkStatus(result.getInt("idEstadoTrabajo"));
                receptionalWork.setIdModality(result.getInt("idModalidad"));
                receptionalWork.setWorkName(result.getString("nombre"));
                receptionalWork.setIdSynodal(result.getString("idSinodal"));
                receptionalWork.setObtainedResults(result.getString("resultadosObtenidos"));
                jobsList.add(receptionalWork);
            }
        } catch (SQLException sqlException) {
            throw sqlException;
        } finally {
            DataBaseManager.closeConnection();
        }
        return jobsList;
    }

    @Override
    public int concludeReceptionalWork(ReceptionalWork receptionalWork, int idReceptionalWork) throws SQLException {
        int result = 0;
        String query = CONCLUDE_RECEPTIONAL_WORK_QUERY;

        try (Connection connection = DataBaseManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, receptionalWork.getIdCoDirector());
            statement.setString(2, receptionalWork.getDescription());
            statement.setString(3, receptionalWork.getFinalDuration());
            statement.setInt(4, receptionalWork.getIdWorkStatus());
            statement.setInt(5, receptionalWork.getIdModality());
            statement.setString(6, receptionalWork.getWorkName());
            statement.setString(7, receptionalWork.getObtainedResults());
            result = statement.executeUpdate();
            if (result == 0){
                throw new SQLException ("Error al concluir trabajo recepcional");
            }
        } catch (SQLException sQLException){
            throw sQLException;
        } finally {
            DataBaseManager.closeConnection();
        } 
        return result;
    }

    @Override
    public ReceptionalWork getReceptionalWork(int idReceptionalWork) throws SQLException {
        String query = GET_RECEPTIONAL_WORK_QUERY;
        ReceptionalWork receptionalWork = new ReceptionalWork();
        try (Connection connection = DataBaseManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idReceptionalWork);
            ResultSet result = statement.executeQuery();
            result.next();
            receptionalWork.setIdReceptionalWork(result.getInt("idTrabajoRecepcional"));
            receptionalWork.setIdStudent(result.getString("idTesista"));
            receptionalWork.setIdDraft(result.getInt("idAnteproyecto"));
            receptionalWork.setIdDirector(result.getString("idDirector"));
            receptionalWork.setIdCoDirector(result.getString("idCoDirector"));
            receptionalWork.setDescription(result.getString("descripcion"));
            receptionalWork.setFinalDuration(result.getString("duracionFinal"));
            receptionalWork.setIdWorkStatus(result.getInt("idEstadoTrabajo"));
            receptionalWork.setIdModality(result.getInt("idModalidad"));
            receptionalWork.setWorkName(result.getString("nombre"));
            receptionalWork.setIdSynodal(result.getString("idSinodal"));
            receptionalWork.setObtainedResults(result.getString("resultadosObtenidos"));
        } catch (SQLException sqlException) {
            throw sqlException;
        } finally {
            DataBaseManager.closeConnection();
        }
        return receptionalWork;
    }

    @Override
    public int addSynodal(String idSynodal, int idReceptionalWork) throws SQLException {
        int result = 0;
        String query = ADD_SYNODAL_QUERY;

        try (Connection connection = DataBaseManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, idSynodal);
            statement.setInt(2, idReceptionalWork);
            result = statement.executeUpdate();
            if (result == 0){
                throw new SQLException ("Error al asignar sinodal");
            }
        } catch (SQLException sQLException){
            throw sQLException;
        } finally {
            DataBaseManager.closeConnection();
        } 
        return result;
    }

    @Override
    public List<ReceptionalWork> getCurrentReceptionalJobsByDirector(String idDirector) throws SQLException {
        String query = GET_CURRENT_RECEPTIONAL_JOBS_QUERY;
        List<ReceptionalWork> jobsList = new ArrayList<>();
        try (Connection connection = DataBaseManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, idDirector);
            statement.setInt(2, CURRENT);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                ReceptionalWork receptionalWork = new ReceptionalWork();
                receptionalWork.setIdReceptionalWork(result.getInt("idTrabajoRecepcional"));
                receptionalWork.setIdStudent(result.getString("idTesista"));
                receptionalWork.setIdDraft(result.getInt("idAnteproyecto"));
                receptionalWork.setIdDirector(result.getString("idDirector"));
                receptionalWork.setIdCoDirector(result.getString("idCoDirector"));
                receptionalWork.setDescription(result.getString("descripcion"));
                receptionalWork.setFinalDuration(result.getString("duracionFinal"));
                receptionalWork.setIdWorkStatus(result.getInt("idEstadoTrabajo"));
                receptionalWork.setIdModality(result.getInt("idModalidad"));
                receptionalWork.setWorkName(result.getString("nombre"));
                receptionalWork.setIdSynodal(result.getString("idSinodal"));
                receptionalWork.setObtainedResults(result.getString("resultadosObtenidos"));
                jobsList.add(receptionalWork);
            }
        } catch (SQLException sqlException) {
            throw sqlException;
        } finally {
            DataBaseManager.closeConnection();
        }
        return jobsList;
    }

    @Override
    public String getReceptionalWorkName(String idUser) throws SQLException {
        String receptionalWorkName = null;
        String query = GET_RECEPCTIONAL_WORK_NAME_BY_STUDENT_QUERY;
        Connection connection = DataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, idUser);
        ResultSet result= statement.executeQuery();
        result.next();
        receptionalWorkName = result.getString("nombre");
        DataBaseManager.closeConnection();
        return receptionalWorkName;
    }
    
}
