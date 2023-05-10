package mx.uv.fei.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mx.uv.fei.implementations.CourseDAO;
import mx.uv.fei.implementations.CourseTableDAO;
import mx.uv.fei.implementations.SchoolPeriodDAO;
import mx.uv.fei.logic.Course;
import mx.uv.fei.logic.CourseTable;
import mx.uv.fei.logic.SchoolPeriod;
import mx.uv.fei.logic.UserTable;
import mx.uv.fei.logic.SSPGER;


/**
 * FXML Controller class
 *
 * @author sue 
 */
public class CourseManageController implements Initializable {
    @FXML
    private Button buttonAssets;

    @FXML
    private Button buttonConsult;

    @FXML
    private Button buttonCreateCourse;

    @FXML
    private Button buttonInactives;

    @FXML
    private Button buttonSearch;

    @FXML
    private TableColumn<CourseTable, String> columnName;

    @FXML
    private TableColumn<CourseTable, String> columnNrc;

    @FXML
    private TableColumn<CourseTable, String> columnSchoolPeriod;

    @FXML
    private TableView<CourseTable> tableCourses;

    @FXML
    private TextField textFieldSearchCourse;
    
    private List<CourseTable> courseList = new ArrayList<>();
    private ObservableList<CourseTable> observableCourseList = FXCollections.observableArrayList();

    @FXML
    void buttonAssets(ActionEvent event) {
        int ACTIVE_COURSE = 1;
        tableCourses.getItems().clear();
        tableCourses.refresh();
        CourseTableDAO courseTableDAO = new CourseTableDAO();
    
        try {
            courseList = courseTableDAO.getCoursesByStatus(ACTIVE_COURSE);
            columnNrc.setCellValueFactory(new PropertyValueFactory<>("nrc"));
            columnSchoolPeriod.setCellValueFactory(new PropertyValueFactory<>("namePeriod"));
            columnSchoolPeriod.setCellValueFactory(new PropertyValueFactory<>("name"));
            ObservableList<CourseTable> observableList = FXCollections.observableList(courseList);
            tableCourses.setItems(observableList);
        } catch (SQLException sQLException) {
            Logger.getLogger(CourseManageController.class.getName()).log(Level.SEVERE, null, sQLException);
        }
    }

    @FXML
    void buttonConsult(ActionEvent event) {
        if (this.tableCourses.getSelectionModel().getSelectedIndex()!= -1) {
            int idCourse = this.tableCourses.getSelectionModel().getSelectedItem().getIdCourse();
            Stage stage = (Stage) this.buttonConsult.getScene().getWindow();
            SSPGER sspger = new SSPGER();
            CourseInformationController.idCourse = idCourse;
            try {
                sspger.showCourseInformation(stage);               
            } catch (IOException iOException) {
                Logger.getLogger(CourseManageController.class.getName()).log(Level.SEVERE, null, iOException);            
            }
        } else {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Debe seleccionar un curso");
            alert.showAndWait();
        }
    }

    @FXML
    void buttonCreateCourse(ActionEvent event) {
        Stage stage = (Stage) this.buttonCreateCourse.getScene().getWindow();
        SSPGER sspger = new SSPGER();
        try {
            sspger.showRegisterCourseWindow(stage);
        } catch (IOException iOException) {
            Logger.getLogger(CourseManageController.class.getName()).log(Level.SEVERE, null, iOException);            
        }
    }

    @FXML
    void buttonInactives(ActionEvent event) {
        int INACTIVE_COURSE = 0;
        tableCourses.getItems().clear();
        tableCourses.refresh();
        CourseTableDAO courseTableDAO = new CourseTableDAO();
    
        try {
            courseList = courseTableDAO.getCoursesByStatus(INACTIVE_COURSE);
            columnNrc.setCellValueFactory(new PropertyValueFactory<>("nrc"));
            columnSchoolPeriod.setCellValueFactory(new PropertyValueFactory<>("namePeriod"));
            columnSchoolPeriod.setCellValueFactory(new PropertyValueFactory<>("name"));
            ObservableList<CourseTable> observableList = FXCollections.observableList(courseList);
            tableCourses.setItems(observableList);
        } catch (SQLException sQLException) {
            Logger.getLogger(CourseManageController.class.getName()).log(Level.SEVERE, null, sQLException);
        }
    }

    @FXML
    void buttonSearch(ActionEvent event) {
         String nrc = textFieldSearchCourse.getText();
         ObservableList<CourseTable> observableUsersList = FXCollections.observableList(courseList);
         try {
            tableCourses.getItems().clear();
            tableCourses.refresh(); 
            CourseDAO courseDAO = new CourseDAO();
            SchoolPeriodDAO schoolPeriodDAO = new SchoolPeriodDAO();
            Course course = courseDAO.getCourseByNrc(nrc);
            SchoolPeriod schoolPeriod = schoolPeriodDAO.getSchoolPeriodById(course.getIdSchoolPeriod());
            CourseTable selectedCourseTable = new CourseTable();
            selectedCourseTable.setIdCourse(course.getIdCourse());
            selectedCourseTable.setName(course.getName());
            selectedCourseTable.setNamePeriod(schoolPeriod.getNamePeriod());
            selectedCourseTable.setNrc(nrc);
            observableCourseList.add(selectedCourseTable);
            tableCourses.setItems(observableCourseList);
            columnNrc.setCellValueFactory(new PropertyValueFactory<>("nrc"));
            columnSchoolPeriod.setCellValueFactory(new PropertyValueFactory<>("namePeriod"));
            columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
         } catch (SQLException sQLException) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo obtener el curso");
            alert.setContentText(sQLException.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTableCourses();
    }

    private void loadTableCourses() {
        CourseTableDAO courseTableDAO = new CourseTableDAO();

        try {
            courseList = courseTableDAO.getAllCourses();
            columnNrc.setCellValueFactory(new PropertyValueFactory<>("nrc"));
            columnSchoolPeriod.setCellValueFactory(new PropertyValueFactory<>("namePeriod"));
            columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
            ObservableList<CourseTable> observableList = FXCollections.observableList(courseList);
            tableCourses.setItems(observableList);
        } catch (SQLException sQLException) {
            Logger.getLogger(AddStudentsToCourseController.class.getName()).log(Level.SEVERE, null, sQLException);
        }
    }    
    
}
