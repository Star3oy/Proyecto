package mx.uv.fei.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class AddActivityController implements Initializable {

    @FXML
    private DatePicker datepickerStart;
    @FXML
    private TextField textFieldTitle;
    @FXML
    private DatePicker datePickerFinish;
    @FXML
    private TextArea textAreaDetails;
    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonCancel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    @FXML
    private void buttonSave(ActionEvent event) {
        Activity activity = new Activity();
        ActivityDAO activityDAO = new ActivityDAO();
        activity.setTitle(textFieldTitle.getText());
        activity.setDetails(textAreaDetails.getText());
        activity.setStatus(1);
        activity.setStartDate(activityDAO.convertLocalDateToDate(datepickerStart.getValue()));
        activity.setStartDate(activityDAO.convertLocalDateToDate(datepickerStart.getValue()));
        
        try {
            activityDAO.addActivity(activity);
        } catch (SQLException ex) {
            Logger.getLogger(AddActivityController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void buttonCancel(ActionEvent event) {
    }
    
}
