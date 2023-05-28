package mx.uv.fei.implementations;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mx.uv.fei.contracts.ISchoolPeriod;
import mx.uv.fei.dataaccess.DataBaseManager;
import mx.uv.fei.logic.SchoolPeriod;

/**
 *
 * @author sue
 */
public class SchoolPeriodDAO implements ISchoolPeriod {
    @Override
    public List<SchoolPeriod> getAllSchoolPeriods() throws SQLException {
        List<SchoolPeriod> schoolPeriods = new ArrayList<>();

        try (Connection connection = DataBaseManager.getConnection()) {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM periodos_escolares";        
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                SchoolPeriod schoolPeriod = new SchoolPeriod();
                schoolPeriod.setIdSchoolPeriod(result.getInt("idPeriodoEscolar"));
                schoolPeriod.setStartDate(new java.util.Date(result.getDate("fechaInicio").getTime()));
                schoolPeriod.setEndDate(new java.util.Date(result.getDate("fechaFin").getTime()));
                schoolPeriod.setStartClassesDate(new java.util.Date(result.getDate("fechaInicioClases").getTime()));
                schoolPeriod.setEndClassesDate(new java.util.Date(result.getDate("fechaFinClases").getTime()));
                schoolPeriod.setNamePeriod(result.getString("nombrePeriodo"));
                schoolPeriods.add(schoolPeriod);
            }
        } catch (SQLException sQLException) {
            throw sQLException;
        } finally {
            DataBaseManager.closeConnection();
        }
        return schoolPeriods; 
    }
    
    @Override
    public SchoolPeriod getSchoolPeriodByName(String nameSchoolPeriod) throws SQLException {
        SchoolPeriod schoolPeriod = new SchoolPeriod();
        try (Connection connection = DataBaseManager.getConnection()) {
            String query = "SELECT * FROM periodos_escolares WHERE nombre = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nameSchoolPeriod);
            ResultSet result = statement.executeQuery();
            result.next();
            schoolPeriod.setIdSchoolPeriod(result.getInt("idPeriodoEscolar"));
            schoolPeriod.setStartDate(new java.util.Date(result.getDate("fechaInicio").getTime()));
            schoolPeriod.setEndDate(new java.util.Date(result.getDate("fechaFin").getTime()));
            schoolPeriod.setStartClassesDate(new java.util.Date(result.getDate("fechaInicioClases").getTime()));
            schoolPeriod.setEndClassesDate(new java.util.Date(result.getDate("fechaFinClases").getTime()));
            schoolPeriod.setNamePeriod(result.getString("nombrePeriodo"));
        } catch (SQLException sQLException) {
            throw sQLException;
        } finally {
            DataBaseManager.closeConnection();
        }
        return schoolPeriod;
    }
    
    public SchoolPeriod getSchoolPeriodById(int idSchoolPeriod) throws SQLException {
        SchoolPeriod schoolPeriod = new SchoolPeriod();
        String query = "SELECT * FROM periodos_escolares WHERE idPeriodoEscolar = ?";
        
        try (Connection connection = DataBaseManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idSchoolPeriod);
            ResultSet result= statement.executeQuery();
            result.next();
            schoolPeriod.setIdSchoolPeriod(result.getInt("idPeriodoEscolar"));
            schoolPeriod.setStartDate(new java.util.Date(result.getDate("fechaInicio").getTime()));
            schoolPeriod.setEndDate(new java.util.Date(result.getDate("fechaFin").getTime()));
            schoolPeriod.setStartClassesDate(new java.util.Date(result.getDate("fechaInicioClases").getTime()));
            schoolPeriod.setEndClassesDate(new java.util.Date(result.getDate("fechaFinClases").getTime()));
            schoolPeriod.setNamePeriod(result.getString("nombrePeriodo"));
        }catch (SQLException sQLException){
            throw sQLException;
        } finally {
            DataBaseManager.closeConnection();
        }
        return schoolPeriod;
    }
}
