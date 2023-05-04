package mx.uv.fei.logic;

import java.util.Date;

/**
 *
 * @author Star3oy
 */
public class Feedback {
    private int idFeedback;
    private String  coment;
    private Date feedBackDate;
    private int idProgress;
    private int idUser;

    public int getIdFeedback() {
        return idFeedback;
    }

    public void setIdFeedback(int idFeedback) {
        this.idFeedback = idFeedback;
    }

    

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public Date getFeedBackDate() {
        return feedBackDate;
    }

    public void setFeedBackDate(Date feedBackDate) {
        this.feedBackDate = feedBackDate;
    }

    public int getIdProgress() {
        return idProgress;
    }

    public void setIdProgress(int idProgress) {
        this.idProgress = idProgress;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    
}
