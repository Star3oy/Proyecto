package mx.uv.fei.controllers;

import java.io.File;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import mx.uv.fei.implementations.DeliverableFileDAO;
import mx.uv.fei.implementations.FeedbackDAO;
import mx.uv.fei.implementations.ProgressDAO;
import mx.uv.fei.logic.DeliverableFile;
import mx.uv.fei.logic.Feedback;
import mx.uv.fei.logic.Progress;

/**
 * FXML Controller class
 *
 * @author Star3oy
 */
public class ProgressController implements Initializable {
    public int idActivity = 2;
    
    @FXML
    private HBox HBoxFileDeliverable;
    
    @FXML
    private VBox VBoxStudentComment;
    
    @FXML
    private Button buttonFeedBackAdvances;

    @FXML
    private Button buttonWithoutFeedback;

    @FXML
    private VBox cardComment;

    @FXML
    private ChoiceBox<Progress> choiceBoxAdvances;

    @FXML
    void buttonFeedBackAdvances(ActionEvent event) {
       int feedBackAdvances = 1;
       fillChoiceBoxAdvancesByStatus(feedBackAdvances);
    }

    @FXML
    void buttonWithoutFeedback(ActionEvent event) {
        int wihtoutFeedback = 0;
        fillChoiceBoxAdvancesByStatus( wihtoutFeedback);
    }        
    
    private void fillChoiceBoxAdvancesByStatus(int status) { 
        ProgressDAO progressDAO = new ProgressDAO();
        List<Progress> progressList = null;
        ObservableList<Progress>  progressObservableList = FXCollections.observableArrayList();
        
    try {
            progressList = progressDAO.getProgressesByStatus(idActivity, status);
        } catch (SQLException ex) {
            Logger.getLogger(ProgressController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        if (progressList != null) {
            progressObservableList.addAll(progressList);
        }    
      choiceBoxAdvances.setItems(progressObservableList);
    }
    
    private void setChoiceBoxAdvances() {
        ProgressDAO progressDAO = new ProgressDAO();
        List<Progress> progressList = null;
        ObservableList<Progress>  progressObservableList = FXCollections.observableArrayList();
        try {
            progressList = progressDAO.getAllProgressesByActivity(idActivity);
        } catch (SQLException ex) {
            Logger.getLogger(ProgressController.class.getName()).log(Level.SEVERE, null, ex);
        }      
        if (progressList != null) {
            progressObservableList.addAll(progressList);
            choiceBoxAdvances.setItems(progressObservableList);
            if(! progressObservableList.isEmpty()) {
                choiceBoxAdvances.setValue(progressObservableList.get(0));
            }
        }         
    }
    
    private void addComments (int idProgress) {
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        List<Feedback> feedbackList = null;
        try {
            feedbackList = feedbackDAO.getAllFeedBackByProgress(idProgress);
        } catch (SQLException ex) {
            Logger.getLogger(ProgressController.class.getName()).log(Level.SEVERE, null, ex);
        }
       try { 
        for (int i = 0; i < feedbackList.size(); i++) {
                FXMLLoader fxmlLoader = new  FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/mx/uv/fei/gui/CommentProgress.fxml"));
                VBox commentBox = fxmlLoader.load();
                CommentProgressController commentController = fxmlLoader.getController();
                commentController.setDataComment(feedbackList.get(i));
                cardComment.getChildren().add(commentBox);

        }
      } catch (IOException ex) {
           Logger.getLogger(ProgressController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    private void addDeliverableFile(int idProgress){
       DeliverableFileDAO deliverableFileDAO = new DeliverableFileDAO();
       DeliverableFile deliverableFile = new DeliverableFile();
        try {
            deliverableFile = deliverableFileDAO.getDeliverableFileByProgress(idProgress);
        } catch (SQLException ex) {
            Logger.getLogger(ProgressController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try { 
            FXMLLoader fxmlLoader = new  FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/mx/uv/fei/gui/FileCard.fxml"));
            HBox Filebox = fxmlLoader.load();
            FileCardController fileCard = fxmlLoader.getController();
            fileCard.setData(deliverableFile);
            HBoxFileDeliverable.getChildren().add(Filebox);
      } catch (IOException ex) {
           Logger.getLogger(ProgressController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    private void addStudentComment (int idProgress){
        ProgressDAO progressDAO = new ProgressDAO();
        String studentComment = "";
        try {
           studentComment = progressDAO.getStudentComment(idProgress);
        } catch (SQLException ex) {
            Logger.getLogger(ProgressController.class.getName()).log(Level.SEVERE, null, ex);
        }
       try { 
                FXMLLoader fxmlLoader = new  FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/mx/uv/fei/gui/CommentProgress.fxml"));
                VBox commentBox = fxmlLoader.load();
                CommentProgressController commentController = fxmlLoader.getController();
                commentController.setDataCommentStudent(studentComment);
                VBoxStudentComment.getChildren().add(commentBox);

      } catch (IOException ex) {
           Logger.getLogger(ProgressController.class.getName()).log(Level.SEVERE, null, ex);
            }   
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.setChoiceBoxAdvances();
        choiceBoxAdvances.setOnAction(e->{
           this.getProgress(); 
        });
    }  
    
    public void getProgress() {
        HBoxFileDeliverable.getChildren().clear();
        VBoxStudentComment.getChildren().clear();
        cardComment.getChildren().clear();
        Progress progressSelected = choiceBoxAdvances.getValue();
        this.addComments(progressSelected.getIdProgress());
        this.addDeliverableFile(progressSelected.getIdProgress());
        this.addStudentComment(progressSelected.getIdProgress());
    }
    
}
