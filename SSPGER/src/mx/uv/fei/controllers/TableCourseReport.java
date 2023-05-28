package mx.uv.fei.controllers;

/**
 *
 * @author Star3oy
 */
public class TableCourseReport {
    String identificator;
    String fullName;
    String reteptionalWork;
    int numberOfActivities;
    int numberOfActivitiesCompleted;

    public TableCourseReport(String identificator, String fullName, String reteptionalWork, int numberOfActivities, int numberOfActivitiesCompleted) {
        this.identificator = identificator;
        this.fullName = fullName;
        this.reteptionalWork = reteptionalWork;
        this.numberOfActivities = numberOfActivities;
        this.numberOfActivitiesCompleted = numberOfActivitiesCompleted;
    }
    
    public String getIdentificator() {
        return identificator;
    }

    public void setIdentificator(String identificator) {
        this.identificator = identificator;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getReteptionalWork() {
        return reteptionalWork;
    }

    public void setReteptionalWork(String reteptionalWork) {
        this.reteptionalWork = reteptionalWork;
    }

    public int getNumberOfActivities() {
        return numberOfActivities;
    }

    public void setNumberOfActivities(int numberOfActivities) {
        this.numberOfActivities = numberOfActivities;
    }

    public int getNumberOfActivitiesCompleted() {
        return numberOfActivitiesCompleted;
    }

    public void setNumberOfActivitiesCompleted(int numberOfActivitiesCompleted) {
        this.numberOfActivitiesCompleted = numberOfActivitiesCompleted;
    }
    
    
}
