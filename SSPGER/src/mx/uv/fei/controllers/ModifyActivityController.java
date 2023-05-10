package mx.uv.fei.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import mx.uv.fei.implementations.ActivityDAO;
import mx.uv.fei.logic.Activity;
/**
 * FXML Controller class
 *
 * @author Star3oy
 */
public class ModifyActivityController implements Initializable {
    public static int  idActivity;
    
    
    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonSave;

    @FXML
    private DatePicker datePickerFinish;

    @FXML
    private DatePicker datepickerStart;

    @FXML
    private TextArea textAreaDetails;

    @FXML
    private TextField textFieldTitle;

    @FXML
    void buttonCancel(ActionEvent event) {

    }

    @FXML
    void buttonSave(ActionEvent event) {

    }
    
    void infoActivity(){
        Activity activity = new Activity();
        ActivityDAO activityDAO = new ActivityDAO();   
        try {
            activity = activityDAO.getActivity(idActivity);
        } catch (SQLException ex) {
            Logger.getLogger(ModifyActivityController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Date dateStart = activity.getStartDate();
        Date dateFinish = activity.getFinishDate();
        textFieldTitle.setText(activity.getTitle());
        textAreaDetails.setText(activity.getDetails());
        datepickerStart.setValue(convertLocalDateToDate(dateStart));
        datePickerFinish.setValue(convertLocalDateToDate(dateFinish));
    }
    
    LocalDate convertLocalDateToDate(Date date){
        LocalDate  localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
       return localDate;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
