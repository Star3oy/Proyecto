package mx.uv.fei.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
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
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import mx.uv.fei.implementations.UserDAO;
import mx.uv.fei.gui.MessageDialog;
import mx.uv.fei.implementations.BlockDAO;
import mx.uv.fei.implementations.CourseDAO;
import mx.uv.fei.implementations.EducationalExperienceDAO;
import mx.uv.fei.implementations.SchoolPeriodDAO;
import mx.uv.fei.implementations.SectionDAO;
import mx.uv.fei.logic.Block;
import mx.uv.fei.logic.Course;
import mx.uv.fei.logic.CourseDataHolder;
import mx.uv.fei.logic.EducationalExperience;
import mx.uv.fei.logic.SchoolPeriod;
import mx.uv.fei.logic.Section;
import mx.uv.fei.logic.User;
import mx.uv.fei.logic.UserTable;
import mx.uv.fei.logic.SSPGER;

/**
 * FXML Controller class
 *
 * @author sue 
 */
public class RegisterCourseController implements Initializable {
    @FXML
    private Button buttonAddStudents;
    @FXML
    private Button buttonCancel;
    @FXML
    private Button buttonSave;
    @FXML
    private ChoiceBox<Block> choiceBoxBlock;
    @FXML
    private ChoiceBox<EducationalExperience> choiceBoxEducationalExperience;
    @FXML
    private ChoiceBox<SchoolPeriod> choiceBoxSchoolPeriod;
    @FXML
    private ChoiceBox<User> choiceBoxProfessor;
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
    private List<UserTable> selectedUsers = new ArrayList<>();
        
    @FXML
    private void buttonAddStudents(ActionEvent event) {
        CourseDataHolder courseDataHolder = CourseDataHolder.getCourseDataHolder();
        
        courseDataHolder.setTextFieldNrcValue(textFieldNrc.getText());
        courseDataHolder.setChoiceBoxEducationalExperienceValue(choiceBoxEducationalExperience.getValue());
        courseDataHolder.setChoiceBoxSchoolPeriodValue(choiceBoxSchoolPeriod.getValue());
        courseDataHolder.setChoiceBoxSectionValue(choiceBoxSection.getValue());
        courseDataHolder.setChoiceBoxBlockValue(choiceBoxBlock.getValue());
        courseDataHolder.setChoiceBoxProfessorValue(choiceBoxProfessor.getValue());
        Stage stage = (Stage) this.buttonAddStudents.getScene().getWindow();
        SSPGER sspger = new SSPGER();
        try {
            sspger.showAddStudentsToCourse(stage);
        } catch (IOException iOException) {
            Logger.getLogger(RegisterCourseController.class.getName()).log(Level.SEVERE, null, iOException);            
        }
    } 

    @FXML
    private void buttonSave(ActionEvent event) {
        if (!isItemEmpty()) {
            if (isNumeric()) {
                Course course = new Course();
                User selectedProfessor = new User();
                CourseDAO courseDAO = new CourseDAO();
                int ACTIVE = 1;
                int SUCCESS = 1;
                
                try {
                    course.setIdEducationalExperience(choiceBoxEducationalExperience.getValue().getIdEducationalExperience());
                    course.setIdSection(choiceBoxSection.getValue().getIdSection());
                    course.setIdBlock(choiceBoxBlock.getValue().getIdBlock());
                    course.setNrc(textFieldNrc.getText());
                    course.setName(choiceBoxEducationalExperience.getValue().getName());
                    course.setIdSchoolPeriod(choiceBoxSchoolPeriod.getValue().getIdSchoolPeriod());
                    course.setStatus(ACTIVE);
                    selectedProfessor = choiceBoxProfessor.getValue();
                    UserTable selectedUserTable = new UserTable();
                    selectedUserTable.setFirstName(selectedProfessor.getFirstName());
                    selectedUserTable.setIdUser(selectedProfessor.getIdUser());
                    selectedUserTable.setType(selectedProfessor.getType());
                    selectedUsers.add(selectedUserTable);
                    if (!courseDAO.isCourseRegistered(course) && !courseDAO.isNrcActive(course)) {
                        if (courseDAO.registerCourse(course, selectedUsers) == SUCCESS) {
                                MessageDialog.showSuccessMessage();
                                CourseDataHolder.getCourseDataHolder().destroyCourseDataHolder();
                        } else {
                            MessageDialog.showErrorDatabaseMessage();
                        }                     
                    } else {
                        MessageDialog.showCourseAlreadyRegisteredMessage();
                    }
                } catch (SQLException sQLException) {
                    Logger.getLogger(RegisterCourseController.class.getName()).log(Level.SEVERE, null, sQLException);
                }   
            } else {
                MessageDialog.showInvalidInformationMessage();
            }   
        } else {
            MessageDialog.showEmptyFieldsMessage();
        }
    }
    
    @FXML
    private void buttonCancel(ActionEvent event) {
        if (MessageDialog.showCancelMessage()) {
            CourseDataHolder.getCourseDataHolder().destroyCourseDataHolder();
            Stage stage = (Stage) this.buttonCancel.getScene().getWindow();
            SSPGER sspger = new SSPGER();
            try {
                sspger.showCourseManage(stage);
            } catch (IOException iOException) {
                Logger.getLogger(RegisterCourseController.class.getName()).log(Level.SEVERE, null, iOException);            
            }
        }
    }     
    
    private boolean isItemEmpty() {
        return choiceBoxBlock.getValue() == null || choiceBoxEducationalExperience.getValue() == null
                || choiceBoxProfessor.getValue() == null || choiceBoxSchoolPeriod == null
                || choiceBoxSection.getValue() == null || textFieldNrc.getText().isEmpty();
    }
    
    private boolean isNumeric() {
        return Pattern.compile("[0-9]*").matcher(textFieldNrc.getText()).matches();
    }
    
    private void setBlocksBox() throws SQLException {
        BlockDAO blockDAO = new BlockDAO();
        List<Block> blocks = null;
        ObservableList<Block> blocksObservableList = FXCollections.observableArrayList();

        try {
            blocks = blockDAO.getAllBlocks();
        } catch (SQLException sQLException) {
            throw sQLException;
        }
        if (blocks != null) {
            blocksObservableList.addAll(blocks); 
            choiceBoxBlock.setItems(blocksObservableList);
            if(!blocksObservableList.isEmpty()) {
                choiceBoxBlock.setValue(blocksObservableList.get(0));
            }
        }
    }
    
    private void setEducationalExperiencesBox() throws SQLException {
        EducationalExperienceDAO EducationalExperienceDAO = new EducationalExperienceDAO();
        List<EducationalExperience> educationalExperiences = null;
        ObservableList<EducationalExperience> educationalExperiencesObservableList = FXCollections.observableArrayList();

        try {
            educationalExperiences = EducationalExperienceDAO.getAllEducationalExperiences();
        } catch (SQLException sQLException) {
            throw sQLException;
        }
        if (educationalExperiences != null) {
            educationalExperiencesObservableList.addAll(educationalExperiences);
            choiceBoxEducationalExperience.setItems(educationalExperiencesObservableList);
            if(!educationalExperiencesObservableList.isEmpty()) {
                choiceBoxEducationalExperience.setValue(educationalExperiencesObservableList.get(0));
            }
        }
    }

    private void setSchoolPeriodsBox() throws SQLException {
        SchoolPeriodDAO schoolPeriodDAO = new SchoolPeriodDAO();
        List<SchoolPeriod> schoolPeriods = null;
        ObservableList<SchoolPeriod> schoolPeriodsNameObservableList = FXCollections.observableArrayList();

        try {
            schoolPeriods = schoolPeriodDAO.getAllSchoolPeriods();
        } catch (SQLException sQLException) {
            throw sQLException;
        }
        if (schoolPeriods != null) {
            schoolPeriodsNameObservableList.addAll(schoolPeriods);
            choiceBoxSchoolPeriod.setItems(schoolPeriodsNameObservableList);
            if(!schoolPeriodsNameObservableList.isEmpty()) {
                choiceBoxSchoolPeriod.setValue(schoolPeriodsNameObservableList.get(0));  
            }
        }
    }     
    
    private void setSectionsBox() throws SQLException {
        SectionDAO sectionDAO = new SectionDAO();
        List<Section> sections = null;
        ObservableList<Section> sectionsNamesObservableList = FXCollections.observableArrayList();

        try {
            sections = sectionDAO.getAllSections();
        } catch (SQLException sQLException) {
            throw sQLException;            
        }
        if (sections != null) {
            sectionsNamesObservableList.addAll(sections);
            choiceBoxSection.setItems(sectionsNamesObservableList);
            if(!sectionsNamesObservableList.isEmpty()) {
                choiceBoxSection.setValue(sectionsNamesObservableList.get(0));
            }
        }
    }

    private void setProfessorsBox() throws SQLException {
        int PROFESSOR = 0;
        UserDAO userDAO = new UserDAO();
        List<User> users = null;
        ObservableList<User> professorsObservableList = FXCollections.observableArrayList();

        try {
            users = userDAO.getUsersByType(PROFESSOR);
        } catch (SQLException sQLException) {
            throw sQLException;
        }
        if (users != null) {
            professorsObservableList.addAll(users);
            choiceBoxProfessor.setItems(professorsObservableList);
            if(!professorsObservableList.isEmpty()) {
                choiceBoxProfessor.setValue(professorsObservableList.get(0));
            }
        }
    }
    
    private void setFormValues() {
        CourseDataHolder course = CourseDataHolder.getCourseDataHolder();
        textFieldNrc.setText(course.getTextFieldNrcValue());
        choiceBoxEducationalExperience.setValue(course.getChoiceBoxEducationalExperienceValue());
        choiceBoxSchoolPeriod.setValue(course.getChoiceBoxSchoolPeriodValue());
        choiceBoxSection.setValue(course.getChoiceBoxSectionValue());
        choiceBoxBlock.setValue(course.getChoiceBoxBlockValue());
        choiceBoxProfessor.setValue(course.getChoiceBoxProfessorValue());
    }
    
    public void setSelectedUsers(List<UserTable> selectedUsers) {
        this.selectedUsers = selectedUsers;
    }
    
    public void loadTableUsers() {
        ObservableList<UserTable> observableList = FXCollections.observableList(selectedUsers);
        columnName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName() + " " + 
                cellData.getValue().getMiddleName()+ " " + cellData.getValue().getLastName()));    
        columnIdentificator.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        tableStudents.setItems(observableList);
    }
       
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setBlocksBox();
            setEducationalExperiencesBox();
            setSchoolPeriodsBox();
            setSectionsBox();
            setProfessorsBox();
            loadTableUsers();
            setFormValues();
        } catch (SQLException sQLException) {
            Logger.getLogger(RegisterCourseController.class.getName()).log(Level.SEVERE, null, sQLException);
            MessageDialog.showErrorDatabaseMessage();
        }
        for (UserTable userTable : selectedUsers) {
            JOptionPane.showMessageDialog(null, userTable.getFirstName());
        }
    }      
}




    
