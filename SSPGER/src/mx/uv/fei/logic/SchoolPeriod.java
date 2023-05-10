package mx.uv.fei.logic;

import java.util.Date;

/**
 *
 * @author Sue
 */
public class SchoolPeriod {
    private int idSchoolPeriod;
    private Date startDate;
    private Date endDate;
    private Date startClassesDate;
    private Date endClassesDate;
    private String namePeriod;

    public void setNamePeriod(String namePeriod) {
        this.namePeriod = namePeriod;
    }

    public void setIdSchoolPeriod(int idSchoolPeriod) {
        this.idSchoolPeriod = idSchoolPeriod;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStartClassesDate(Date startClassesDate) {
        this.startClassesDate = startClassesDate;
    }

    public void setEndClassesDate(Date endClassesDate) {
        this.endClassesDate = endClassesDate;
    }

    public int getIdSchoolPeriod() {
        return idSchoolPeriod;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartClassesDate() {
        return startClassesDate;
    }

    public Date getEndClassesDate() {
        return endClassesDate;
    }

    public String getNamePeriod() {
        return namePeriod;
    }
     
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object.getClass() != this.getClass()) {
            return false;
        }
        final SchoolPeriod other = (SchoolPeriod) object;
        return this.idSchoolPeriod == other.idSchoolPeriod &&
           ((this.startDate == null) ? (other.startDate == null) : this.startDate.equals(other.startDate)) &&
           ((this.endDate == null) ? (other.endDate == null) : this.endDate.equals(other.endDate)) &&
           ((this.startClassesDate == null) ? (other.startClassesDate == null) : this.startClassesDate.equals(other.startClassesDate)) &&
           ((this.endClassesDate == null) ? (other.endClassesDate == null) : this.endClassesDate.equals(other.endClassesDate)) &&
           ((this.namePeriod == null) ? (other.namePeriod == null) : this.namePeriod.equals(other.namePeriod));
    }
    
    @Override
    public String toString() {
        return namePeriod;
    }
}
