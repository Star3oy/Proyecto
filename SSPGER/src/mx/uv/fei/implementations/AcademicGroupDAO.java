package mx.uv.fei.implementations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mx.uv.fei.contracts.IAcademicGroup;
import mx.uv.fei.dataaccess.DataBaseManager;
import mx.uv.fei.logic.AcademicGroup;

/**
 *
 * @author Sue
 */
public class AcademicGroupDAO implements IAcademicGroup{
    @Override
    public List<AcademicGroup> getAllAcademicGroups() throws SQLException {
        List<AcademicGroup> academicGroups = new ArrayList<>();
        
        try (Connection connection = DataBaseManager.getConnection()) {
            String query = "SELECT * FROM cuerpo_academico";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                AcademicGroup academicGroup = new AcademicGroup();
                academicGroup.setIdAcademicGroup(resultSet.getInt("idCuerpoAcademico"));
                academicGroup.setAcademicGroup(resultSet.getString("cuerpoAcademico"));
                academicGroups.add(academicGroup);
            }
        } catch (SQLException sQLException) {
            throw sQLException;
        } finally {
            DataBaseManager.closeConnection();
        }
        return academicGroups;
    }
}
