package mx.uv.fei.implementations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mx.uv.fei.contracts.ILgac;
import mx.uv.fei.dataaccess.DataBaseManager;
import mx.uv.fei.logic.Lgac;

/**
 *
 * @author Sue
 */
public class LgacDAO implements ILgac {
    @Override
    public List<Lgac> getAllLgacs() throws SQLException {
        List<Lgac> lgacs = new ArrayList<>();
        
        try (Connection connection = DataBaseManager.getConnection()) {
            String query = "SELECT * FROM lgac";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Lgac lgac = new Lgac();
                lgac.setIdLgac(resultSet.getInt("idLgac"));
                lgac.setLgac(resultSet.getString("lgac"));
                lgac.setIdAcademicGroup(resultSet.getInt("idCuerpoaAcademico"));
                lgacs.add(lgac);
            }
        } catch (SQLException sQLException) {
            throw sQLException;
        } finally {
            DataBaseManager.closeConnection();
        }
        return lgacs;
    }
}
