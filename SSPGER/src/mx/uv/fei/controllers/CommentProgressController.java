package mx.uv.fei.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import mx.uv.fei.implementations.FeedbackDAO;
import mx.uv.fei.implementations.UserDAO;
import mx.uv.fei.logic.Feedback;
import mx.uv.fei.logic.User;

/**
 * FXML Controller class
 *
 * @author Star3oy
 */
public class CommentProgressController implements Initializable {
    public int idProgress;
    @FXML
    private Label labelAuthor;

    @FXML
    private Label labelComment;

    @FXML
    private TextArea textAreaComment;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    public void setDataComment(Feedback feedback) {
        UserDAO userDAO = new UserDAO();
        User user = null;
        try {
            user = userDAO.getUser(feedback.getIdUser());
        } catch (SQLException ex) {
            Logger.getLogger(CommentProgressController.class.getName()).log(Level.SEVERE, null, ex);
        }
        textAreaComment.setText(feedback.getComent());
        labelAuthor.setText(user.getFirstName() +" " + user.getMiddleName() + " " + user.getLastName());   
    }
      
    public void setDataCommentStudent(String comment){
        textAreaComment.setText(comment);
        labelComment.setText("Tu Comentario");
        labelAuthor.setText("");
    }
}
