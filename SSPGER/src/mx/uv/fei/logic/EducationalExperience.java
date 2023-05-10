package mx.uv.fei.logic;

/**
 *
 * @author sue 
 */
public class EducationalExperience {
    private int idEducationalExperience;
    private String name;
    private int credits;

    public int getIdEducationalExperience() {
        return idEducationalExperience;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public void setIdEducationalExperience(int idEducationalExperience) {
        this.idEducationalExperience = idEducationalExperience;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
    
    @Override
    public boolean equals(Object object) {
        if(object == null) {
            return false;
        }
        if (object.getClass() != this.getClass()) {
            return false;
        }
        final EducationalExperience other = (EducationalExperience) object;
        return this.idEducationalExperience == other.idEducationalExperience &&
           (this.name == null ? other.name == null : this.name.equals(other.name)) &&
           this.credits == other.credits;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
