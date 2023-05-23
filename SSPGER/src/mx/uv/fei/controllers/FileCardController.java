package mx.uv.fei.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.Document;
import mx.uv.fei.implementations.DeliverableFileDAO;
import mx.uv.fei.logic.DeliverableFile;

/**
 * FXML Controller class
 *
 * @author Star3oy
 */
public class FileCardController implements Initializable { 
    final String USER_DIR = System.getProperty("user.dir");
    final String PROJECT_PATH= USER_DIR + "\\src\\mx\\uv\\fei\\files\\";
    public String fileName = "";
    
    
    @FXML
    private ImageView imageViewAdobeIcon;

    @FXML
    private ImageView imageViewDownloaderIcon;

    @FXML
    private Label labelFileTitle;

    @FXML
    private Label labelIdentificator;
    
    @FXML
    void downloadImageClicked(MouseEvent event) {
         DeliverableFileDAO deliverableFileDAO = new DeliverableFileDAO();
        try {
            DeliverableFile deliverableFile = deliverableFileDAO.getDeliverableFile(fileName); 
            Stage DirectoryStage = new Stage();
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(DirectoryStage);  
            if(selectedDirectory == null) {
                JOptionPane.showMessageDialog(null, "Se debe seleccionar la ruta",
                        "No se ha seleccionado ninguna ruta", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Path sourcePath = Path.of(PROJECT_PATH + deliverableFile.getName());
                Path destinationPath = Path.of(selectedDirectory.getAbsolutePath(), sourcePath.getFileName().toString());
                try {
                    Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ex) {
                    Logger.getLogger(FileCardController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FileCardController.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
    }    
    
    public void setData (DeliverableFile deliverableFile) {
        String deliverableFileName = deliverableFile.getName();
        int maximumSize = 13;
        if(deliverableFileName.length()>maximumSize) {
            String deliverableFileShortName = deliverableFileName.substring(0,maximumSize);
            labelFileTitle.setText(deliverableFileShortName +"...");
        } else {
           labelFileTitle.setText(deliverableFile.getName());
        }
        fileName = deliverableFileName;
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
}
