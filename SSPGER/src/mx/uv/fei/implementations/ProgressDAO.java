package mx.uv.fei.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.uv.fei.contracts.IProgress;
import mx.uv.fei.dataaccess.DataBaseManager;
import mx.uv.fei.logic.DeliverableFile;
import mx.uv.fei.logic.Progress;

/**
 *
 * @author sue
 */
public class ProgressDAO implements IProgress{
    private static final String GET_STUDENTPROGRESSES = "SELECT comentario FROM Avances WHERE idAvance = ?";
    private static final String GET_ALL_PROGRESSES_QUERY_BY_ACTIVITY = "SELECT * FROM Avances WHERE idActividad = ?";
    private static final String GET_PROGRESS_BY_STATUS_QUERY = "SELECT * FROM Avances WHERE idActividad = ? AND estado = ?";
    private static final String ADD_PROGRESS_QUERY = "insert into avances(idAvance, comentario, idUsuario, estado, idActividad) "
       + "values(?, ?, ?, ?, ?)";
    private static final String ADD_DELIVERABLE_FILE_QUERY = "insert into entregables(tipo, nombre, ruta, idAvance) values"
            + "(?, ?, ?, ?)";
    
    @Override
    public String getStudentComment(int idProgress) throws SQLException {
        String studentComment;
        String query = GET_STUDENTPROGRESSES;
        Connection connection = DataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idProgress);
        ResultSet result= statement.executeQuery();
        result.next();
        studentComment = result.getString("comentario");
        return studentComment;
    }
   
    @Override
    public List<Progress> getAllProgressesByActivity(int idActivity) throws SQLException {
        List<Progress> progressList = new ArrayList<>();
        
        try (Connection connection = DataBaseManager.getConnection()) {
            String query = GET_ALL_PROGRESSES_QUERY_BY_ACTIVITY;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idActivity);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Progress progress = new Progress();
                progress.setIdProgress(result.getInt("idAvance"));
                progress.setComent(result.getString("comentario"));
                progress.setProgressDate(result.getDate("fechaEntrega"));
                progress.setIdUser(result.getString("idUsuario"));
                progress.setStatus(result.getInt("estado"));
                progress.setIdActivity((result.getInt("idActividad")));
                progressList.add(progress);
            }
        } finally {
            DataBaseManager.closeConnection();
        }
        return progressList;   
    }
    
    @Override
    public List<Progress> getProgressesByStatus(int idActivity, int status) throws SQLException {
        List<Progress> progressList = new ArrayList<>();
        
        try (Connection connection = DataBaseManager.getConnection()) {
            String query = GET_PROGRESS_BY_STATUS_QUERY;
            PreparedStatement statement = connection.prepareStatement(query);            
            statement.setInt(1, idActivity);
            statement.setInt(2, status);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Progress progress = new Progress();
                progress.setIdProgress(result.getInt("idAvance"));
                progress.setComent(result.getString("comentario"));
                progress.setProgressDate(result.getDate("fechaEntrega"));
                progress.setIdUser(result.getString("idUsuario"));
                progress.setStatus(result.getInt("estado"));
                progressList.add(progress);
            }
        } finally {
            DataBaseManager.closeConnection();
        }
        return progressList; 
    }
    
    @Override
    public int sendProgressTransaction(Progress progress, DeliverableFile file) throws SQLException {
        int response = 0;
        
        try {
            DataBaseManager.getConnection().setAutoCommit(false);

            PreparedStatement progressStatement = DataBaseManager.getConnection().prepareStatement(ADD_PROGRESS_QUERY);
            progressStatement.setInt(1, progress.getIdProgress());
            progressStatement.setString(2, progress.getComent());
            progressStatement.setString(3, progress.getIdUser());
            progressStatement.setInt(4,progress.getStatus());
            progressStatement.setInt(5, progress.getIdActivity());
            
            response =  progressStatement.executeUpdate();
            
            PreparedStatement fileStatement = DataBaseManager.getConnection().prepareStatement(ADD_DELIVERABLE_FILE_QUERY);
            fileStatement.setString(1, file.getType());
            fileStatement.setString(2, file.getName());
            fileStatement.setString(3, file.getPathName());
            fileStatement.setInt(4,file.getIdProgress());
            
            response = response + fileStatement.executeUpdate();
            
            DataBaseManager.getConnection().commit();
        } catch (SQLException ex) {
            DataBaseManager.getConnection().rollback();
        } finally {
            DataBaseManager.getConnection().close();
        }
        return response;
    }
}
