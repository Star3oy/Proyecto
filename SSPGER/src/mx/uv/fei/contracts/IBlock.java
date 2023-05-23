package mx.uv.fei.contracts;

import java.sql.SQLException;
import java.util.List;
import mx.uv.fei.logic.Block;

/**
 *
 * @author Sue
 */
public interface IBlock {
    public List<Block> getAllBlocks() throws SQLException;
}
