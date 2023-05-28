package mx.uv.fei.controllers;

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
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mx.uv.fei.implementations.CourseDAO;
import mx.uv.fei.logic.Course;
import mx.uv.fei.logic.User;
import com.itextpdf.text.Document;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Star3oy
 */
public class CoursereportController implements Initializable {
    public static int idCourse = 42;

    ObservableList<TableCourseReport> list = FXCollections.observableArrayList();
    
    @FXML
    private TableView<TableCourseReport> TableCourseReport;

    @FXML
    private Button buttonDownloadPDF;

    @FXML
    private TableColumn<TableCourseReport, String> columnIdentificator;

    @FXML
    private TableColumn<TableCourseReport, String> columnName;

    @FXML
    private TableColumn<TableCourseReport, Integer> columnNumberOfActivities;

    @FXML
    private TableColumn<TableCourseReport, Integer> columnNumberOfActivitiesCompleted;

    @FXML
    private TableColumn<TableCourseReport, String> columnReceptionalWork;

    @FXML
    private Label labelBlock;

    @FXML
    private Label labelCourseName;

    @FXML
    private Label labelNRC;

    @FXML
    private Label labelSection; 
    
    @FXML
    void buttonDownloadPDF(ActionEvent event) throws DocumentException {
      try {
            Document reportDocument = new Document();
            Stage DirectoryStage = new Stage();
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(DirectoryStage);

            if(selectedDirectory == null){

            } else {
                PdfWriter.getInstance(reportDocument, new FileOutputStream(selectedDirectory + "\\ReporteCurso.pdf"));
                reportDocument.open();
            
                Font titleFont = new Font();
                Font subtitleFont = new Font();
            
                titleFont.setColor(45, 82, 100);
                titleFont.setSize(20);
                titleFont.setStyle(Font.BOLD);
                subtitleFont.setSize(14);
                subtitleFont.setStyle(Font.BOLD);
            
                Paragraph title = new Paragraph("Reporte General del Curso", titleFont);
            
                reportDocument.add(title);
                reportDocument.add(Chunk.NEWLINE);
                reportDocument.add(createReportTable());
 
                reportDocument.close();

            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CoursereportController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(CoursereportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void fillTable() {
        List<User> userList = new ArrayList<>();
        CourseDAO courseDAO = new CourseDAO();
        try {
            userList = courseDAO.getStudentsOfCourse(idCourse);
        } catch (SQLException ex) {
            Logger.getLogger(CoursereportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            String receptionalWorkName = null;
            int numberOfActivities = 0;
            int numberOfActivitiesCompleted = 0;
            try {
                numberOfActivities = courseDAO.numberOfActivities(user.getIdUser());
                numberOfActivitiesCompleted = courseDAO.numberOfActivitiesCompleted(user.getIdUser());
                receptionalWorkName = courseDAO.getReceptionalWorkName(user.getIdUser());
                list.add(new TableCourseReport(user.getIdUser(), user.getFirstName(), receptionalWorkName, numberOfActivities,numberOfActivitiesCompleted));
            } catch (SQLException ex) {
                Logger.getLogger(CoursereportController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        TableCourseReport.setItems(list);
        columnIdentificator.setCellValueFactory(new PropertyValueFactory<TableCourseReport, String>("identificator"));
        columnName.setCellValueFactory(new PropertyValueFactory<TableCourseReport, String>("fullName"));
        columnReceptionalWork.setCellValueFactory(new PropertyValueFactory<TableCourseReport, String>("reteptionalWork"));
        columnNumberOfActivities.setCellValueFactory(new PropertyValueFactory<TableCourseReport, Integer>("numberOfActivities"));
        columnNumberOfActivitiesCompleted.setCellValueFactory(new PropertyValueFactory<TableCourseReport, Integer>("numberOfActivitiesCompleted"));
    }
    
    private void setDataCourse() {
        CourseDAO courseDAO = new CourseDAO();
        Course course = new Course();
        try {
            course = courseDAO.getCourseById(idCourse);
        } catch (SQLException ex) {
            Logger.getLogger(CoursereportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        labelNRC.setText("NRC: "+ course.getNrc());
        labelCourseName.setText(course.getNrc());
    }
    
    private PdfPTable createReportTable() {
        PdfPTable projectTable = new PdfPTable(5);
        Font textFont = new Font();
        textFont.setStyle(Font.BOLD);
        
        PdfPCell identificatorTitleCell = new PdfPCell (new Paragraph ("Matricula:", textFont));
        PdfPCell nameTitleCell = new PdfPCell (new Paragraph ("Nombre:", textFont));
        PdfPCell receptionalWorkTitleCell = new PdfPCell (new Paragraph ("Trabajo Recepcional:", textFont));
        PdfPCell numberOfActivitiesTitleCell = new PdfPCell (new Paragraph ("Número de actividades:", textFont));
        PdfPCell numberOfActivitiesCompletedTitleCell = new PdfPCell (new Paragraph ("Actividades completadas:", textFont));

        projectTable.addCell(identificatorTitleCell);
        projectTable.addCell(nameTitleCell);
        projectTable.addCell(receptionalWorkTitleCell);
        projectTable.addCell(numberOfActivitiesTitleCell);
        projectTable.addCell(numberOfActivitiesCompletedTitleCell);
        
        
        ObservableList<TableCourseReport> datos = TableCourseReport.getItems();
        
        for (TableCourseReport fila : datos) {
            String identificator = columnIdentificator.getCellObservableValue(fila).getValue();
            String name = columnName.getCellObservableValue(fila).getValue();
            String recepcionalWork = columnReceptionalWork.getCellObservableValue(fila).getValue();
            int numberOfActivities = columnNumberOfActivities.getCellObservableValue(fila).getValue();
            int numberOfActivitiesCompleted = columnNumberOfActivitiesCompleted.getCellObservableValue(fila).getValue();
            String numberOfActivitiesString = Integer.toString(numberOfActivities);
            String numberOfActivitiesCompletedString = Integer.toString(numberOfActivitiesCompleted);

            PdfPCell identificatorCell = new PdfPCell(new Paragraph(identificator));
            PdfPCell nameCell = new PdfPCell(new Paragraph(name));
            PdfPCell recepcionalWorkCell = new PdfPCell(new Paragraph(recepcionalWork));
            PdfPCell numberOfActivitiesCell = new PdfPCell(new Paragraph(numberOfActivitiesString));
            PdfPCell numberOfActivitiesCompletedCell = new PdfPCell(new Paragraph(numberOfActivitiesCompletedString));
            
             projectTable.addCell(identificatorCell);
             projectTable.addCell(nameCell);
             projectTable.addCell(recepcionalWorkCell);
             projectTable.addCell(numberOfActivitiesCell);
            projectTable.addCell(numberOfActivitiesCompletedCell);
    }
       
        return projectTable;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     fillTable();
     setDataCourse();
     
    }
}
