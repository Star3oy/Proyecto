
package mx.uv.fei.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javax.swing.JOptionPane;
import mx.uv.fei.logic.Feedback;
import java.util.Date;
import mx.uv.fei.implementations.FeedbackDAO;

/**
 * FXML Controller class
 *
 * @author Star3oy
 */
public class FeedBackController implements Initializable {
        public static int idProgress;
        public static String idUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonProgressInformation;

    @FXML
    private Button buttonSend;

    @FXML
    private Button buttonToAdd;

    @FXML
    private Button buttonToProvideFeedback;
    
    @FXML
    private TextArea textAreaComent;

    @FXML
    void buttonCancel(ActionEvent event) {

    }

    @FXML
    void buttonProgressInformation(ActionEvent event) {

    }

    @FXML
    void buttonSend(ActionEvent event) {
        Date actualDate = new  Date();
        java.sql.Date sqldate = new java.sql.Date(actualDate.getTime());
        Feedback feedback = new Feedback();
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        feedback.setComent(textAreaComent.getText());
        feedback.setFeedBackDate(sqldate);
        feedback.setIdProgress(idProgress);
        feedback.setIdUser(idUser);      
        try {
                feedbackDAO.addFeedback(feedback);
            } catch (SQLException ex) {
                Logger.getLogger(FeedBackController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }      
}
