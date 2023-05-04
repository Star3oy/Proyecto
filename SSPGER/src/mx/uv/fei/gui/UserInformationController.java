package mx.uv.fei.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import mx.uv.fei.bussinesslogic.UserDAO;
import mx.uv.fei.logic.User;


/**
 * FXML Controller class
 *
 * @author Star3oy
 */
public class UserInformationController  implements Initializable {
    private final int STUDENT_ROL = 4;
    private final int PROFESSOR_ROL = 2;
    public static String idUser;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            infoUser();
    }    
    
    @FXML
    private Button buttonDeactivate;

    @FXML
    private Button buttonModify;

    @FXML
    private CheckBox checkBoxProfessor;

    @FXML
    private CheckBox checkBoxStudent;

    @FXML
    private TextField textFieldFirstName;

    @FXML
    private TextField textFieldIdentificator;

    @FXML
    private TextField textFieldInstitutionalEmail;

    @FXML
    private TextField textFieldMiddleName;

    @FXML
    private TextField textFieldSecondName;

    @FXML
    void buttonDeactivate(ActionEvent event) {
        UserDAO userDAO = new UserDAO();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Desactivar Usuario");
        alert.setContentText("¿Está seguro que desea desactivar al usuario? No podrá volver a activarse");
        Optional<ButtonType> action= alert.showAndWait();
        if(action.get().equals(ButtonType.OK)) {
            try {
                userDAO.disableUser(textFieldIdentificator.getText());
                JOptionPane.showMessageDialog(null, "El usuario se ha desactivado correctamente",
                 "Usuario Desactivado", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(UserInformationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo conectar con la base de datos. Inténtelo más tarde o hágalo más tarde", 
                                        "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    void buttonModify(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("modifyUser.fxml"));
        Parent root;
        
        ModifyUserController.idUser = textFieldIdentificator.getText();
        try {
            root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Stage myStage = (Stage) this.buttonModify.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(UserInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void infoUser() {
     User user = new User();
     UserDAO userDAO = new UserDAO();
     
     try {
          user = userDAO.getUser(idUser);
     } catch (SQLException sQLExcpetion) {
         JOptionPane.showMessageDialog(null, "No se pudo conectar con la base de datos. Inténtelo más tarde o hágalo más tarde", 
                                        "ERROR", JOptionPane.ERROR_MESSAGE);   
     }  
     textFieldIdentificator.setText(user.getIdUser());
     textFieldIdentificator.setEditable(false);
     textFieldFirstName.setText(user.getFirstName());
     textFieldFirstName.setEditable(false);
     textFieldMiddleName.setText(user.getMiddleName());
     textFieldMiddleName.setEditable(true);
     textFieldSecondName.setText(user.getLastName());
     textFieldSecondName.setEditable(false);
     textFieldInstitutionalEmail.setText(user.getInstitutionalEmail());
     textFieldInstitutionalEmail.setEditable(false);
     if(user.getIdRole() == STUDENT_ROL){
         checkBoxStudent.setSelected(true);        
     } else {
         checkBoxProfessor.setSelected(true);      
     }
     checkBoxProfessor.setDisable(true);
     checkBoxStudent.setDisable(true);
     
     
    }
    
}
