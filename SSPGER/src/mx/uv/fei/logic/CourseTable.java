package mx.uv.fei.logic;

/**
 *
 * @author sue 
 */
public class CourseTable {
    private String nrc;
    private String namePeriod;
    private String name;
    private int idCourse;

    public CourseTable() {
        this.nrc = "";
        this.namePeriod = "";
        this.name = "";
        this.idCourse = 0;
    }

    public String getNrc() {
        return nrc;
    }

    public String getNamePeriod() {
        return namePeriod;
    }

    public String getName() {
        return name;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public void setNamePeriod(String namePeriod) {
        this.namePeriod = namePeriod;
    }

    public void setName(String name) {
        this.name = name;
    }
}
