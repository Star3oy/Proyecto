
package mx.uv.fei.gui;

import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;
import mx.uv.fei.bussinesslogic.DeliverableFileDAO;
import mx.uv.fei.logic.DeliverableFile;
import mx.uv.fei.logic.Feedback;
import java.util.Date;
import mx.uv.fei.bussinesslogic.FeedbackManagerDAO;

/**
 * FXML Controller class
 *
 * @author Star3oy
 */
public class FeedBackController implements Initializable {
    
    final String PATH_DESTINATION = "C:\\Users\\eduar\\OneDrive\\Escritorio\\Destino\\";
    List<DeliverableFile> fileList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        FeedbackManagerDAO feedbackManagerDAO = new FeedbackManagerDAO();
        feedback.setComent(textAreaComent.getText());
        feedback.setFeedBackDate(sqldate);
        
        try {
            feedbackManagerDAO.feedbackAdditionTransition(feedback, this.fileList);
        } catch (SQLException ex) {
            Logger.getLogger(FeedBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    void buttonToAdd(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        Path origenPath = Paths.get(selectedFile.getAbsolutePath());
        Path destinationPath = Paths.get(PATH_DESTINATION+ selectedFile.getName());
        
        if (selectedFile != null) {
         
                String fileName = selectedFile.getName();
                String extentionName = fileName.substring(fileName.lastIndexOf(".") + 1, selectedFile.getName().length());
                DeliverableFile deliverableFile = new DeliverableFile();
                DeliverableFileDAO deliverableFileDAO = new DeliverableFileDAO();
                
            try {
                Files.copy(origenPath, destinationPath);              
            } catch (IOException ex) {
                Logger.getLogger(FeedBackController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                deliverableFile.setName(fileName);
                deliverableFile.setPathName(PATH_DESTINATION);
                deliverableFile.setType(extentionName);
                fileList.add(deliverableFile);
                
        } else {
             JOptionPane.showMessageDialog(null, "Se debe seleccionar al menos un entregable",
                                        "No ha seleccionado ningún documento", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }

    void saveFileInformation(){
        
    }
    
    @FXML
    void buttonToProvideFeedback(ActionEvent event) {

    }
    
    
}
