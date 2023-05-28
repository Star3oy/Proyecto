package mx.uv.fei.logic;


/**
 *
 * @author Sue
 */
public class Section {
    private int idSection;
    private int section;
    
    public void setIdSection(int idSection) {
        this.idSection = idSection;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public int getIdSection() {
        return idSection;
    }

    public int getSection() {
        return section;
    } 
    
    @Override
    public String toString() {
        return String.valueOf(section);
    }
}
