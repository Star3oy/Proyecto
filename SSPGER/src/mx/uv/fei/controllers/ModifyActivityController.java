package mx.uv.fei.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author Star3oy
 */
public class ModifyActivityController implements Initializable {
    
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}
