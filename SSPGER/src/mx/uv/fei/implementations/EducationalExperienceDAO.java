package mx.uv.fei.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mx.uv.fei.contracts.IEducationalExperience;
import mx.uv.fei.dataaccess.DataBaseManager;
import mx.uv.fei.logic.EducationalExperience;

/**
 *
 * @author sue 
 */
public class EducationalExperienceDAO implements IEducationalExperience {
    @Override
    public List<EducationalExperience> getAllEducationalExperiences() throws SQLException {
        List<EducationalExperience> educationalExperiences = new ArrayList<>();
        
        try (Connection connection = DataBaseManager.getConnection()) {
            String query = "SELECT * FROM experiencias_educativas";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                EducationalExperience educationalExperience = new EducationalExperience();
                educationalExperience.setIdEducationalExperience(result.getInt("idExperienciaEducativa"));
                educationalExperience.setName(result.getString("nombre"));
                educationalExperience.setCredits(result.getInt("creditos"));
                educationalExperiences.add(educationalExperience);
            }
        } catch (SQLException sQLException) {
            throw sQLException;
        } finally {
            DataBaseManager.closeConnection();
        }
        return educationalExperiences; 
    }
    
    @Override
    public EducationalExperience getEducationalExperienceByName(String name) throws SQLException {
        EducationalExperience educationalExperience = new EducationalExperience();
        String query = "SELECT * FROM experiencias_educativas WHERE nombre = ?";
        try (Connection connection = DataBaseManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet result= statement.executeQuery();
            result.next();
            educationalExperience.setIdEducationalExperience(result.getInt("idExperienciaEducativa"));
            educationalExperience.setName(result.getString("nombre"));
            educationalExperience.setCredits(result.getInt("creditos"));
        }catch (SQLException sQLException){
            throw sQLException;
        } finally {
            DataBaseManager.closeConnection();
        }
        return educationalExperience;
    }
    
    public EducationalExperience getEducationalExperienceById(int idEducationalExperience) throws SQLException {
        EducationalExperience educationalExperience = new EducationalExperience();
        String query = "SELECT * FROM experiencias_educativas WHERE idExperienciaEducativa = ?";
        try (Connection connection = DataBaseManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idEducationalExperience);
            ResultSet result= statement.executeQuery();
            result.next();
            educationalExperience.setIdEducationalExperience(result.getInt("idExperienciaEducativa"));
            educationalExperience.setName(result.getString("nombre"));
            educationalExperience.setCredits(result.getInt("creditos"));
        }catch (SQLException sQLException){
            throw sQLException;
        } finally {
            DataBaseManager.closeConnection();
        }
        return educationalExperience;
    }
}
