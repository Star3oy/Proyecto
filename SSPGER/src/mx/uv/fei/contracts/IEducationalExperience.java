package mx.uv.fei.contracts;

import java.sql.SQLException;
import java.util.List;
import mx.uv.fei.logic.EducationalExperience;

/**
 *
 * @author sue
 */
public interface IEducationalExperience {
    List<EducationalExperience> getAllEducationalExperiences() throws SQLException;
    EducationalExperience getEducationalExperienceByName(String name) throws SQLException;
}
