package mx.uv.fei.contracts;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import mx.uv.fei.logic.SchoolPeriod;

/**
 *
 * @author sue
 */
public interface ISchoolPeriod {
    List<SchoolPeriod> getAllSchoolPeriods() throws SQLException;
    SchoolPeriod getSchoolPeriodByName(String nameSchoolPeriod) throws SQLException;
}
