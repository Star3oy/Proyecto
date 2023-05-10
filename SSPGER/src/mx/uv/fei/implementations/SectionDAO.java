package mx.uv.fei.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mx.uv.fei.contracts.ISection;
import mx.uv.fei.dataaccess.DataBaseManager;
import mx.uv.fei.logic.Section;

/**
 *
 * @author Sue
 */
public class SectionDAO implements ISection{
    @Override
    public List<Section> getAllSections() throws SQLException {
        List<Section> sections = new ArrayList<>();
        
        try (Connection connection = DataBaseManager.getConnection()) {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM secciones";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Section section = new Section();
                section.setIdSection(resultSet.getInt("idSeccion"));
                section.setSection(resultSet.getInt("seccion"));
                sections.add(section);
            }
        } catch (SQLException sQLException) {
            throw sQLException;
        } finally {
            DataBaseManager.closeConnection();
        }       
        return sections;
    }
    
    @Override
    public Section getSectionById(int idSection) throws SQLException {
        Section section = new Section();
        try (Connection connection = DataBaseManager.getConnection()) {
            String query = "SELECT * FROM secciones WHERE idSeccion = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idSection);
            ResultSet result = statement.executeQuery();
            result.next();
            section.setIdSection(result.getInt("idSeccion"));
            section.setSection(result.getInt("seccion"));
        } catch (SQLException sQLException) {
            throw sQLException;
        } finally {
            DataBaseManager.closeConnection();
        }
        return section;
    }
}
