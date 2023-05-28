package mx.uv.fei.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import mx.uv.fei.implementations.ActivityDAO;
import mx.uv.fei.implementations.ProgressDAO;
import mx.uv.fei.logic.Activity;
import mx.uv.fei.logic.DeliverableFile;
import mx.uv.fei.logic.Progress;

/**
 * FXML Controller class
 *
 * @author cris9
 */
public class SendProgressController implements Initializable {
    private static final int DELIVERED = 1;
    public static String studentId = "zs21nose";
    public static String title = "titulo Actividad";
    public static int idActivity = 3;
    private static String filePath = "";
    private static String fileName = "";
    private static String fileType = "";

    @FXML
    private Pane paneHead;
    @FXML
    private Label labelSytem;
    @FXML
    private Label labelWindow;
    @FXML
    private Label labelUV;
    @FXML
    private Pane paneActivityInfo;
    @FXML
    private Label labelProgressInfo;
    @FXML
    private Label labelActivity;
    @FXML
    private Label labelActivityName;
    @FXML
    private Pane paneComments;
    @FXML
    private Label labelComments;
    @FXML
    private TextArea textAreaComments;
    @FXML
    private Pane paneEvidences;
    @FXML
    private Label labelEvidence;
    @FXML
    private Label labelFileName;
    @FXML
    private ImageView imageViewFile;
    @FXML
    private Label labelAdd;
    @FXML
    private Label labelMessage;
    @FXML
    private Button buttonSendProgress;
    @FXML
    private Button buttonCancelSendProgress;
    @FXML
    private ImageView imageViewAddFile;
    
    @FXML
    private void imageViewAddFile(MouseEvent event) {
        Stage window = (Stage) imageViewAddFile.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar archivo");
            java.io.File selectedFile = fileChooser.showOpenDialog(window);
            if (selectedFile != null) {
                filePath = selectedFile.getAbsolutePath();
                fileName = selectedFile.getName();
                fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
                labelFileName.setText(fileName);
            }
    }

    @FXML
    private void buttonSendProgress(ActionEvent event) {
        if (!isItemEmpty()) {
            Progress progress = new Progress();
            ProgressDAO progressDao = new ProgressDAO();
            progress.setIdProgress(idActivity);
            progress.setComent(textAreaComments.getText());
            progress.setIdUser(studentId);
            progress.setStatus(DELIVERED);
            progress.setIdActivity(idActivity);
            try {
                if (progressDao.sendProgressTransaction(progress, getFile()) == 0) {
                    JOptionPane.showMessageDialog(null, "El avance se envió correctamente.",
                                   "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo conectar con la base de datos. Inténtelo más tarde o hágalo más tarde", 
                                        "ERROR", JOptionPane.ERROR_MESSAGE);   
                }
            } catch (SQLException ex) {
                Logger.getLogger(SendProgressController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se puede dejar ningún campo vacío. Por favor, ingrese toda la información solicitada",
                        "AVISO", JOptionPane.WARNING_MESSAGE);
        }
        
    }

    @FXML
    private void buttonCancelSendProgress(ActionEvent event) {
    }
    
    private boolean isItemEmpty() {
        return textAreaComments.getText().isEmpty() || filePath.isEmpty();
    }
    
    private DeliverableFile getFile() {
        DeliverableFile file = new DeliverableFile();
        file.setType(fileType);
        file.setName(fileName);
        file.setPathName(fileName);
        file.setIdProgress(idActivity);
        return file;
    }
    
    private Activity getActivity() {
        Activity activity = new Activity();
        ActivityDAO activityDao = new ActivityDAO();
        try {
            activity = activityDao.getActivity(title);
        } catch (SQLException ex) {
            Logger.getLogger(SendProgressController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return activity;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelActivityName.setText(getActivity().getTitle());
    }    

    
    
}
