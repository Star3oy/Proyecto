package mx.uv.fei.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mx.uv.fei.implementations.UserDAO;
import mx.uv.fei.logic.User;

/**
 * FXML Controller class
 *
 * @author Star3oy
 */
public class GestiónUsuariosController implements Initializable {

    ObservableList<TableUsers> list = FXCollections.observableArrayList();
      
    @FXML
    private TableColumn<TableUsers, String> columnName;
    
    @FXML
    private TableColumn<TableUsers, String>  columIdentificator;

    @FXML
    private TableView<TableUsers> tableUsers;
    
    @FXML
    private Button buttonSearch;

    @FXML
    private Button buttonAssets;

    @FXML
    private Button buttonConsult;

    @FXML
    private Button buttonCreateUser;

    @FXML
    private Button buttonInactives;

    @FXML
    private TextField textFieldSearchUser;
    
     @Override
    public void initialize(URL url, ResourceBundle rb) { 
           fillTable();
    }   
   
    @FXML
    void buttonAssets(ActionEvent event) throws SQLException {
        int ACTIVES = 1;
        this.fillTableByStatus(ACTIVES);
    }
    
    @FXML
    void buttonInactives(ActionEvent event) throws SQLException {
        int INACTIVES = 0;
       this.fillTableByStatus(INACTIVES);
    }
    
    @FXML
    void buttonSearch(ActionEvent event) throws SQLException {
        String idUser = textFieldSearchUser.getText();
        tableUsers.getItems().clear();
        tableUsers.refresh(); 
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUser(idUser);
        list.add(new TableUsers (user.getIdUser(), user.getFirstName()));
        tableUsers.setItems(list);
        columIdentificator.setCellValueFactory(new PropertyValueFactory<TableUsers, String>("identificator"));
        columnName.setCellValueFactory(new PropertyValueFactory<TableUsers, String>("name"));
    }
       
    @FXML
    void buttonConsult(ActionEvent event) {
        String identificator = this.tableUsers.getSelectionModel().getSelectedItem().getIdentificator();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mx/uv/fei/gui/UserInformation.fxml"));
        Parent root;    
        UserInformationController.idUser = identificator;
        if(identificator != null) {
        try {
            root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Stage myStage = (Stage) this.buttonConsult.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(UserInformationController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        } else {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Debe seleccionar un usuario");
            alert.showAndWait();
        }
    }

    @FXML
    void buttonCreateUser(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("registerUser.fxml"));
        Parent root;
    
        try {
            root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Stage myStage = (Stage) this.buttonCreateUser.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(GestiónUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void fillTable () {
     UserDAO userDAO = new UserDAO();
     List<User> userList;
        try {
            userList = userDAO.getUserList();
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            list.add(new TableUsers (user.getIdUser(), user.getFirstName()));
        }
          } catch (SQLException ex) {
            Logger.getLogger(GestiónUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
     tableUsers.setItems(list);
     columIdentificator.setCellValueFactory(new PropertyValueFactory<TableUsers, String>("identificator"));
     columnName.setCellValueFactory(new PropertyValueFactory<TableUsers, String>("name"));
    }
    
    void fillTableByStatus(int status){
    tableUsers.getItems().clear();
    tableUsers.refresh();
    UserDAO userDAO = new UserDAO();
     List<User> userList = null;
        try {
            userList = userDAO.getUsersByStatus(status);
        } catch (SQLException ex) {
            Logger.getLogger(GestiónUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            list.add(new TableUsers (user.getIdUser(), user.getFirstName()));
        }
     tableUsers.setItems(list);
     columIdentificator.setCellValueFactory(new PropertyValueFactory<TableUsers, String>("identificator"));
     columnName.setCellValueFactory(new PropertyValueFactory<TableUsers, String>("name"));
    }
}
   
