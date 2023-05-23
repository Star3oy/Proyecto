package mx.uv.fei.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mx.uv.fei.gui.MessageDialog;
import mx.uv.fei.implementations.UserDAO;
import mx.uv.fei.implementations.UserTableDAO;
import mx.uv.fei.logic.User;
import mx.uv.fei.logic.UserTable;
import mx.uv.fei.logic.SSPGER;

/**
 * FXML Controller class
 *
 * @author sue 
 */
public class AddStudentsToCourseController implements Initializable {
    @FXML
    private Button buttonCancel;
    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonSearch;
    @FXML
    private TableColumn<UserTable, CheckBox> columnCheck;
    @FXML
    private TableColumn<UserTable, String> columnIdentificator;
    @FXML
    private TableColumn<UserTable, String> columnName;
    @FXML
    private TableView<UserTable> tableStudents;
    @FXML
    private TextField textFieldSearchUser;
    private List<UserTable> usersList = new ArrayList<>();
    private List<UserTable> userSelected = new ArrayList<>();
    
    private void loadTableUsers() {
        UserTableDAO userTableDAO = new UserTableDAO();

        try {
            usersList = userTableDAO.getStudents();
            columnName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName() + " " + 
                    cellData.getValue().getMiddleName()+ " " + cellData.getValue().getLastName()));    
            columnIdentificator.setCellValueFactory(new PropertyValueFactory<>("idUser"));
            columnCheck.setCellValueFactory(new PropertyValueFactory<>("userSelected"));
            ObservableList<UserTable> observableList = FXCollections.observableList(usersList);
            tableStudents.setItems(observableList);
        } catch (SQLException sQLException) {
            Logger.getLogger(AddStudentsToCourseController.class.getName()).log(Level.SEVERE, null, sQLException);
        }
    }

    @FXML
    private void buttonCancel(ActionEvent event) {
        if (MessageDialog.showCancelMessage()) {
            Stage stage = (Stage) this.buttonCancel.getScene().getWindow();
            SSPGER sspger = new SSPGER();
            try {
                sspger.showRegisterCourseWindow(stage);
            } catch (IOException iOException) {
                Logger.getLogger(AddStudentsToCourseController.class.getName()).log(Level.SEVERE, null, iOException);            
            }
        }
    }
    
    @FXML
    private void buttonSave(ActionEvent event) {
        ObservableList<UserTable> rowsUserTables = tableStudents.getItems();
        for (UserTable userTable : rowsUserTables) {
            if (userTable.getUserSelected().isSelected()) {
                userSelected.add(userTable);
            }
        }
        Stage stage = (Stage) this.buttonSave.getScene().getWindow();
        SSPGER sspger = new SSPGER();
        try {
            sspger.showRegisterCourseWindow(stage, userSelected);
        } catch (IOException iOException) {
            Logger.getLogger(RegisterCourseController.class.getName()).log(Level.SEVERE, null, iOException);            
        }
    }

    @FXML
    private void buttonSearch(ActionEvent event) {
        String idUser = textFieldSearchUser.getText();
        ObservableList<UserTable> observableUsersList = FXCollections.observableList(usersList);
        try {
            tableStudents.getItems().clear();
            tableStudents.refresh(); 
            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUserById(idUser);
            UserTable selectedUserTable = new UserTable();
            selectedUserTable.setFirstName(user.getFirstName());
            selectedUserTable.setMiddleName(user.getMiddleName());
            selectedUserTable.setLastName(user.getLastName());
            selectedUserTable.setIdUser(user.getIdUser());
            selectedUserTable.setType(user.getType());
            observableUsersList.add(selectedUserTable);
            tableStudents.setItems(observableUsersList);
            columnName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName() + " " + 
                    cellData.getValue().getMiddleName()+ " " + cellData.getValue().getLastName()));    
            columnIdentificator.setCellValueFactory(new PropertyValueFactory<UserTable, String>("idUser"));
            columnCheck.setCellValueFactory(new PropertyValueFactory<UserTable, CheckBox>("userSelected"));
        } catch (SQLException sQLException) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo obtener el usuario");
            alert.showAndWait();
        }
    }
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTableUsers();
    }      
}
