package mx.uv.fei.logic;

/**
 *
 * @author cris9
 */
public class ReceptionalWork {
    private int idReceptionalWork;
    private String idStudent;
    private int idDraft;
    private String idDirector;
    private String idCoDirector;
    private String description;
    private String finalDuration;
    private int idWorkStatus;
    private int idModality;
    private String workName;
    private String idSynodal;
    private String obtainedResults;

    public int getIdReceptionalWork() {
        return idReceptionalWork;
    }

    public void setIdReceptionalWork(int idReceptionalWork) {
        this.idReceptionalWork = idReceptionalWork;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public int getIdDraft() {
        return idDraft;
    }

    public void setIdDraft(int idDraft) {
        this.idDraft = idDraft;
    }

    public String getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(String idDirector) {
        this.idDirector = idDirector;
    }
    
    public String getIdCoDirector() {
        return idCoDirector;
    }

    public void setIdCoDirector(String idCoDirector) {
        this.idCoDirector = idCoDirector;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFinalDuration() {
        return finalDuration;
    }

    public void setFinalDuration(String finalDuration) {
        this.finalDuration = finalDuration;
    }

    public int getIdWorkStatus() {
        return idWorkStatus;
    }

    public void setIdWorkStatus(int idWorkStatus) {
        this.idWorkStatus = idWorkStatus;
    }

    public int getIdModality() {
        return idModality;
    }

    public void setIdModality(int idModality) {
        this.idModality = idModality;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getIdSynodal() {
        return idSynodal;
    }

    public void setIdSynodal(String idSynodal) {
        this.idSynodal = idSynodal;
    }

    public String getObtainedResults() {
        return obtainedResults;
    }

    public void setObtainedResults(String obtainedResults) {
        this.obtainedResults = obtainedResults;
    }
    
    @Override
    public boolean equals(Object object) {
        if((object == null) || (object.getClass() != this.getClass())) {
            return false;
        } 
       final ReceptionalWork otherReceptionalWork = (ReceptionalWork) object;
       return this.idReceptionalWork == otherReceptionalWork.idReceptionalWork
                && (this.idStudent == null ? otherReceptionalWork.idStudent == null : this.idStudent.equals(otherReceptionalWork.idStudent))
                && this.idDraft == otherReceptionalWork.idDraft
                && (this.idDirector == null ? otherReceptionalWork.idDirector == null : this.idDirector.equals(otherReceptionalWork.idDirector))
                && (this.idCoDirector == null ? otherReceptionalWork.idCoDirector == null : this.idCoDirector.equals(otherReceptionalWork.idCoDirector))
                && (this.description == null ? otherReceptionalWork.description == null : this.description.equals(otherReceptionalWork.description))
                && (this.finalDuration == null ? otherReceptionalWork.finalDuration == null : this.finalDuration.equals(otherReceptionalWork.finalDuration))
                && this.idWorkStatus == otherReceptionalWork.idWorkStatus
                && this.idModality == otherReceptionalWork.idModality
                && (this.workName == null ? otherReceptionalWork.workName == null : this.workName.equals(otherReceptionalWork.workName))
                && (this.idSynodal == null ? otherReceptionalWork.idSynodal == null : this.idSynodal.equals(otherReceptionalWork.idSynodal))
                && (this.obtainedResults == null ? otherReceptionalWork.obtainedResults == null : this.obtainedResults.equals(otherReceptionalWork.obtainedResults));
    }
    
    @Override
    public String toString(){
        return workName;
    }
    
}
