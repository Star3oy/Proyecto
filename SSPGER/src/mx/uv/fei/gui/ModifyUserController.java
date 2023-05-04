package mx.uv.fei.gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import mx.uv.fei.bussinesslogic.UserDAO;
import mx.uv.fei.logic.User;

/**
 * FXML Controller class
 *
 * @author Star3oy
 */
public class ModifyUserController implements Initializable  {
    public static String idUser;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            infoUser();
    }    
    
    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonSave;

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
    void buttonCancel(ActionEvent event) {

    }
    
    @FXML
    void checkBoxProfessor(ActionEvent event) {
         if(checkBoxProfessor.isSelected()) {
            checkBoxStudent.setDisable(true);
        } else  {
           checkBoxStudent.setDisable(false);
        }
            
    }

    @FXML
    void checkBoxStudent(ActionEvent event) {
        if(checkBoxStudent.isSelected()) {
            checkBoxProfessor.setDisable(true);
        } else  {
           checkBoxProfessor.setDisable(false);
        }
    }

    @FXML
    void buttonSave(ActionEvent event) {
        int STUDENT_ROL = 4;
        int PROFESSOR_ROL = 2;
        int ACTIVE = 1;
        
        User user = new User();
        user.setIdUser(textFieldIdentificator.getText());
        user.setFirstName(textFieldFirstName.getText());
        user.setMiddleName(textFieldMiddleName.getText());
        user.setLastName(textFieldSecondName.getText());
        user.setInstitutionalEmail(textFieldInstitutionalEmail.getText());
        user.setIdStatus(ACTIVE);
        if(checkBoxStudent.isSelected()) {
            user.setIdRole(STUDENT_ROL);
        } else if (checkBoxProfessor.isSelected()) {
            user.setIdRole(PROFESSOR_ROL);
        }   
        UserDAO instance = new UserDAO();
        
     try{
           if(instance.modifyUser(user, "12345") == 1) {     
            JOptionPane.showMessageDialog(null, "La información se modificó correctamente en el sistema",
                                        "Modificación exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo conectar con la base de datos. Inténtelo más tarde o hágalo más tarde", 
                                        "ERROR", JOptionPane.ERROR_MESSAGE); 
            }
        } catch (SQLException ex){
              
        }
        
        
    }
    
     void infoUser(){
     User user = new User();
     UserDAO userDAO = new UserDAO();
     try {
          user = userDAO.getUser(idUser);
     } catch (SQLException sQLExcpetion) {
         JOptionPane.showMessageDialog(null, "No se pudo conectar con la base de datos. Inténtelo más tarde o hágalo más tarde", 
                                        "ERROR", JOptionPane.ERROR_MESSAGE);   
     }  
     textFieldIdentificator.setText(user.getIdUser());
     textFieldFirstName.setText(user.getFirstName());
     textFieldMiddleName.setText(user.getMiddleName());
     textFieldSecondName.setText(user.getLastName());
     textFieldInstitutionalEmail.setText(user.getInstitutionalEmail());   
    }
    

}
