package mx.uv.fei.contracts;

import java.sql.SQLException;
import java.util.List;
import mx.uv.fei.logic.Lgac;

/**
 *
 * @author Sue
 */
public interface ILgac {
    public List<Lgac> getAllLgacs() throws SQLException;
}
