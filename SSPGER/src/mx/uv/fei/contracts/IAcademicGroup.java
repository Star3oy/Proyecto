package mx.uv.fei.contracts;

import java.sql.SQLException;
import java.util.List;
import mx.uv.fei.logic.AcademicGroup;

/**
 *
 * @author Sue
 */
public interface IAcademicGroup {
        public List<AcademicGroup> getAllAcademicGroups() throws SQLException;
}
