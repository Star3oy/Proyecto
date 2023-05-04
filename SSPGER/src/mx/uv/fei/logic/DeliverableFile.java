
package mx.uv.fei.logic;

/**
 *
 * @author Star3oy
 */

public class DeliverableFile {
    private int idDeliverableFile;
    private String type;
    private String name;
    private String pathName;
    private int idProgress;
    private int idFeedback;



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdDeliverableFile() {
        return idDeliverableFile;
    }

    public void setIdDeliverableFile(int idDeliverableFile) {
        this.idDeliverableFile = idDeliverableFile;
    }

    public int getIdProgress() {
        return idProgress;
    }

    public void setIdProgress(int idProgress) {
        this.idProgress = idProgress;
    }

    public int getIdFeedback() {
        return idFeedback;
    }

    public void setIdFeedback(int idFeedback) {
        this.idFeedback = idFeedback;
    }
    
    
    
}
