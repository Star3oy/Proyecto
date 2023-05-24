
package mx.uv.fei.logic;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author sue
 */
public class Progress {
    private int idProgress;
    private String coment;
    private Date progressDate;
    private String idUser;
    private int status;
    private int idActivity;

    public int getIdProgress() {
        return idProgress;
    }

    public void setIdProgress(int idProgress) {
        this.idProgress = idProgress;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public Date getProgressDate() {
        return progressDate;
    }

    public void setProgressDate(Date progressDate) {
        this.progressDate = progressDate;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public int getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(int idActivity) {
        this.idActivity = idActivity;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = dateFormat.format(progressDate);
        return dateString;
    }
    
    @Override 
    public boolean equals(Object object) {
        if((object == null) || (object.getClass() != this.getClass())) {
            return false;
        } 
       final Progress otherProgress = (Progress) object;
       return this.idProgress == otherProgress.idProgress
            && (this.coment == null ? otherProgress.coment == null : this.coment.equals(otherProgress.coment))
            && this.progressDate == otherProgress.progressDate  
            && (this.idUser == null ? otherProgress.idUser == null : this.idUser.equals(otherProgress.idUser))
            && this.status == otherProgress.status
            && this.idActivity == otherProgress.idActivity;
        }
}
