package mx.uv.fei.contracts;

import java.sql.SQLException;
import java.util.List;
import mx.uv.fei.logic.Section;

/**
 *
 * @author Sue
 */
public interface ISection {
    public List<Section> getAllSections() throws SQLException;
    public Section getSectionById(int idSection) throws SQLException;
}
