package mx.uv.fei.logic;

/**
 *
 * @author Sue
 */
public class AcademicGroup {
    private int idAcademicGroup;
    private String academicGroup;

    public void setIdAcademicGroup(int idAcademicGroup) {
        this.idAcademicGroup = idAcademicGroup;
    }

    public void setAcademicGroup(String academicGroup) {
        this.academicGroup = academicGroup;
    }

    public int getIdAcademicGroup() {
        return idAcademicGroup;
    }

    public String getAcademicGroup() {
        return academicGroup;
    }
    
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object.getClass() != this.getClass()) {
            return false;
        }
        final AcademicGroup other = (AcademicGroup) object;
        return idAcademicGroup == other.idAcademicGroup
                && (academicGroup == null ? other.academicGroup == null : academicGroup.equals(other.academicGroup));
    }
}
