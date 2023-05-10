package mx.uv.fei.controllers;

import java.util.Date;

/**
 *
 * @author Star3oy
 */
public class TableActivities {
    String title;
    Date startDate;
    Date finishDate;
    String status;

    public TableActivities(String title, Date startDate, Date finishDate, String status) {
        this.title = title;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
