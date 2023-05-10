package mx.uv.fei.logic;


import java.util.Date;

/**
 *
 * @author Star3oy
 */

public class Activity {
    private int idActivity;
    private String title;
    private String details;
    private Date  startDate;
    private Date finishDate;
    private int status;
    private String idUser;

    public int getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(int idActivity) {
        this.idActivity = idActivity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
    
        @Override 
        public boolean equals(Object object) {
        if((object == null) || (object.getClass() != this.getClass())) {
            return false;
        } 
       final Activity otherActivity = (Activity) object;
       return this.idActivity == otherActivity.idActivity
        && (this.title == null ? otherActivity.title == null : this.title.equals(otherActivity.title))
        && (this.details == null ? otherActivity.details == null : this.title.equals(otherActivity.details))
        &&   this.startDate == otherActivity.startDate
        && this.finishDate == otherActivity.finishDate
        && (this.idUser == null ? otherActivity.idUser == null : this.idUser.equals(otherActivity.idUser));
        }
    
    
}
