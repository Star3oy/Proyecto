package mx.uv.fei.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mx.uv.fei.contracts.IBlock;
import mx.uv.fei.dataaccess.DataBaseManager;
import mx.uv.fei.logic.Block;

/**
 *
 * @author Sue
 */
public class BlockDAO implements IBlock {
    @Override
    public List<Block> getAllBlocks() throws SQLException {
        List<Block> blocks = new ArrayList<>();
        
        try (Connection connection = DataBaseManager.getConnection()) {
            String query = "SELECT * FROM bloques";        
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Block block = new Block();
                block.setIdBlock(resultSet.getInt("idBloque"));
                block.setBlock(resultSet.getInt("bloque"));
                blocks.add(block);
            }
        } catch (SQLException sQLException) {
            throw sQLException;
        } finally {
            DataBaseManager.closeConnection();
        }
        return blocks;
    }
    
    public Block getBlockById(int idBlock) throws SQLException {
        Block block = new Block();
        String query = "SELECT * FROM bloques WHERE idBloque = ?";
        
        try (Connection connection = DataBaseManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idBlock);
            ResultSet result= statement.executeQuery();
            result.next();
            block.setIdBlock(result.getInt("idBloque"));
            block.setBlock(result.getInt("bloque"));
        }catch (SQLException sQLException){
            throw sQLException;
        } finally {
            DataBaseManager.closeConnection();
        }
        return block;
    }
}
