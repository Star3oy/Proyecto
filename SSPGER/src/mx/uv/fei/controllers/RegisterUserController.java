package mx.uv.fei.controllers;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import mx.uv.fei.implementations.UserDAO;
import mx.uv.fei.implementations.UserManagerDAO;
import mx.uv.fei.logic.Login;
import mx.uv.fei.logic.User;

public class RegisterUserController implements Initializable{

    
    @FXML
    private Button buttonDisable;

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
    private TextField textFieldPassword;
    
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
    private void buttonSave(ActionEvent event) throws SQLException{
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
        
        Login login = new Login();
        
        login.setIdUser(textFieldIdentificator.getText());
        login.setPassword(textFieldPassword.getText());
        
        UserManagerDAO userManagerDAO = new UserManagerDAO();
        
     try{
           if(userManagerDAO.userAdditionTransition(user, login) == 2) {     
            JOptionPane.showMessageDialog(null, "La información se registró correctamente en el sistema",
                                        "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo conectar con la base de datos. Inténtelo más tarde o hágalo más tarde", 
                                        "ERROR", JOptionPane.ERROR_MESSAGE); 
            }
        } catch (SQLException ex){
              
        } finally {
            this.cleanTextField();
        }
    }
    
    private void buttonCancel (ActionEvent event){
     
    }

    
  private boolean isNotItemEmpty (){
      boolean result = false;
      
      if (textFieldFirstName.getText().isEmpty() || textFieldIdentificator.getText().isEmpty() ||
          textFieldInstitutionalEmail.getText().isEmpty() ||textFieldMiddleName.getText().isEmpty() ||
           textFieldSecondName.getText().isEmpty()) {
          result = true;
      }
      
      return result;
  }
  
  private boolean isValidEmail () {
      boolean result = false;
      String email = textFieldInstitutionalEmail.getText();
      
      if (email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}") ) {
         result = true; 
      }
      return result;
  }
  

    private boolean emailDontExist ()  throws SQLException {
     boolean result = false;
     String email = textFieldInstitutionalEmail.getText();
     UserDAO userDAO = new UserDAO();
     try {
     if(userDAO.verifyUserEmail(email) == 0) {
         result = true;
        }
     } catch (SQLException sQLException) {
         throw sQLException;
     }
        return result;
    }

    public void cleanTextField(){
    textFieldFirstName.setText("");
    textFieldIdentificator.setText("");
    textFieldInstitutionalEmail.setText("");
    textFieldMiddleName.setText("");
    textFieldSecondName.setText("");
    textFieldPassword.setText("");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
         
}