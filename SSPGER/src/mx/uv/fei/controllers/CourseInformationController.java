package mx.uv.fei.controllers;

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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import mx.uv.fei.gui.MessageDialog;
import mx.uv.fei.implementations.BlockDAO;
import mx.uv.fei.implementations.CourseDAO;
import mx.uv.fei.implementations.EducationalExperienceDAO;
import mx.uv.fei.implementations.SchoolPeriodDAO;
import mx.uv.fei.implementations.SectionDAO;
import mx.uv.fei.logic.Block;
import mx.uv.fei.logic.Course;
import mx.uv.fei.logic.EducationalExperience;
import mx.uv.fei.logic.SchoolPeriod;
import mx.uv.fei.logic.Section;
import mx.uv.fei.logic.User;
import mx.uv.fei.logic.UserTable;

/**
 * FXML Controller class
 *
 * @author sue 
 */
public class CourseInformationController implements Initializable {
    @FXML
    private Button buttonDisable;
    @FXML
    private Button buttonModify;
    @FXML
    private ChoiceBox<Block> choiceBoxBlock;
    @FXML
    private ChoiceBox<EducationalExperience> choiceBoxEducationalExperience;
    @FXML
    private ChoiceBox<User> choiceBoxProfessor;
    @FXML
    private ChoiceBox<SchoolPeriod> choiceBoxSchoolPeriod;
    @FXML
    private ChoiceBox<Section> choiceBoxSection;
    @FXML
    private TableColumn<UserTable, String> columnIdentificator;
    @FXML
    private TableColumn<UserTable, String> columnName;
    @FXML
    private TableView<UserTable> tableStudents;
    @FXML
    private TextField textFieldNrc;
    public static int idCourse;

    @FXML
    private void buttonDisable(ActionEvent event) {
        int SUCCESS = 1;
        if (MessageDialog.showDiseableConfirmation()) {
            CourseDAO courseDAO = new CourseDAO();
            try {
                int result = courseDAO.diseableCourse(idCourse);
                if (result == SUCCESS) {
                    MessageDialog.showSuccessMessage();
                } else {
                    MessageDialog.showErrorDatabaseMessage();
                }
            } catch (SQLException sQLException) {
                Logger.getLogger(CourseInformationController.class.getName()).log(Level.SEVERE, null, sQLException);            
           }
       }
    }

    @FXML
    private void buttonModify(ActionEvent event) {

    }
    
    private void loadInformationCourse() {
        Course course = new Course();
        EducationalExperienceDAO educationalExperienceDAO = new EducationalExperienceDAO();
        CourseDAO courseDAO = new CourseDAO();
        BlockDAO blockDAO = new BlockDAO();
        SectionDAO sectionDAO = new SectionDAO();
        SchoolPeriodDAO schoolPeriodDAO = new SchoolPeriodDAO();
        
        try {
            course = courseDAO.getCourseById(idCourse);
            textFieldNrc.setText(course.getNrc());
            textFieldNrc.setEditable(false);
            choiceBoxEducationalExperience.setValue(educationalExperienceDAO.
                    getEducationalExperienceById(course.getIdEducationalExperience()));
            choiceBoxSchoolPeriod.setValue(schoolPeriodDAO.getSchoolPeriodById(course.getIdSchoolPeriod()));
            choiceBoxSection.setValue(sectionDAO.getSectionById(course.getIdSection()));
            choiceBoxBlock.setValue(blockDAO.getBlockById(course.getIdBlock()));
            choiceBoxProfessor.setValue(courseDAO.getProfessorOfCourse(course.getIdCourse())); 
        } catch (SQLException sQLException) {
            MessageDialog.showErrorDatabaseMessage();
        }
    }
    
    private void loadTableStudents() throws SQLException {
        List<UserTable> studentsOnCourseList = new ArrayList<>();
        studentsOnCourseList = setStudents();
        ObservableList<UserTable> observableUserTableList = FXCollections.observableList(studentsOnCourseList);
        columnName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName() + " " + 
                cellData.getValue().getMiddleName()+ " " + cellData.getValue().getLastName()));    
        columnIdentificator.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        tableStudents.setItems(observableUserTableList);
    }
    
    private List<UserTable> setStudents() throws SQLException {
        CourseDAO courseDAO = new CourseDAO();
        List<User> studentsList = courseDAO.getStudentsOfCourse(idCourse);
        List<UserTable> userTableList = new ArrayList<>();
        for (User user : studentsList) {
            UserTable userTable = new UserTable();
            userTable.setFirstName(user.getFirstName());
            userTable.setMiddleName(user.getMiddleName());            
            userTable.setLastName(user.getLastName());
            userTable.setIdUser(user.getIdUser());
            userTable.setType(user.getType());
            userTableList.add(userTable);
        }
       return userTableList; 
    }
    
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadInformationCourse();
        try {
            loadTableStudents();
        } catch (SQLException sQLException) {
            MessageDialog.showErrorDatabaseMessage();;
        }
    }
}
