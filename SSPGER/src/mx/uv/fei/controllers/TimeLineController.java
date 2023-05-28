package mx.uv.fei.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mx.uv.fei.implementations.ActivityDAO;
import mx.uv.fei.logic.Activity;

/**
 * FXML Controller class
 *
 * @author Star3oy
 */
public class TimeLineController implements Initializable {

    ObservableList<TableActivities> list = FXCollections.observableArrayList();
    
    @FXML
    private Button buttonAddActivity;

    @FXML
    private Button buttonConsult;

    @FXML
    private Button buttonDone;

    @FXML
    private Button buttonModify;

    @FXML
    private Button buttonNotDone;

    @FXML
    private TableColumn<TableActivities, String> columnActivity;

    @FXML
    private TableColumn<TableActivities, Date> columnFinishDate;

    @FXML
    private TableColumn<TableActivities, Date> columnStartDate;

    @FXML
    private TableColumn<TableActivities, String> columnStatus;

    @FXML
    private TableView<TableActivities> tableActivities;

    @FXML
    void buttonAddActivity(ActionEvent event) {

    }

    @FXML
    void buttonConsult(ActionEvent event) {

    }

    @FXML
    void buttonDone(ActionEvent event) {
        int activityStatusDone = 1;
        fillTableByStatus(activityStatusDone);
    }
    
    @FXML
    void buttonNotDone(ActionEvent event) {
        int activityStatusNotDone = 0;
        fillTableByStatus(activityStatusNotDone);
    }

    @FXML
    void buttonModify(ActionEvent event) {
        String identificator = this.tableActivities.getSelectionModel().getSelectedItem().getTitle();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mx/uv/fei/gui/ModifyActivity.fxml"));
        Parent root;    
        ModifyActivityController.title = identificator;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       fillTable();
    }   
    
    void fillTable(){
        ActivityDAO activityDAO = new ActivityDAO();
        List<Activity> activityList;
        String status = "Realizada";
        try {
            activityList = activityDAO.getActivityList();
            for (int i = 0; i < activityList.size(); i++) {
                Activity activity = activityList.get(i);
                if(activity.getStatus() == 0){
                    status = "Sin realizar";
                }
              list.add(new TableActivities (activity.getTitle(), activity.getStartDate(), activity.getFinishDate(), status));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TimeLineController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableActivities.setItems(list);
        columnActivity.setCellValueFactory(new PropertyValueFactory<TableActivities, String>("title"));
        columnStartDate.setCellValueFactory(new PropertyValueFactory<TableActivities, Date>("startDate"));
        columnFinishDate.setCellValueFactory(new PropertyValueFactory<TableActivities, Date>("finishDate"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<TableActivities, String>("status"));
    }
    
     void fillTableByStatus(int activityStatus){
        ActivityDAO activityDAO = new ActivityDAO();
        List<Activity> activityList;
        String status = "Realizada";
        if (activityStatus == 0) {
            status = "Sin realizar";
        }
        try {
            activityList = activityDAO.getActivityListByStatus(activityStatus);
            for (int i = 0; i < activityList.size(); i++) {
                Activity activity = activityList.get(i);
              list.add(new TableActivities (activity.getTitle(), activity.getStartDate(), activity.getFinishDate(), status));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TimeLineController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableActivities.setItems(list);
        columnActivity.setCellValueFactory(new PropertyValueFactory<TableActivities, String>("title"));
        columnStartDate.setCellValueFactory(new PropertyValueFactory<TableActivities, Date>("startDate"));
        columnFinishDate.setCellValueFactory(new PropertyValueFactory<TableActivities, Date>("finishDate"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<TableActivities, String>("status"));
    }
    
    
}
