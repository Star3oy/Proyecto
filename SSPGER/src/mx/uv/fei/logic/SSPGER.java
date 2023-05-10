package mx.uv.fei.logic;

import java.io.IOException;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mx.uv.fei.controllers.RegisterCourseController;

/**
 *
 * @author Star3oy
 */
public class SSPGER extends Application {
    
    @Override
    public void start(Stage stage) throws  IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/mx/uv/fei/gui/courseManage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void showRegisterCourseWindow(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/mx/uv/fei/gui/registerCourse.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void showCourseInformation(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/mx/uv/fei/gui/courseInformation.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void showCourseManage(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/mx/uv/fei/gui/courseManage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void showRegisterCourseWindow(Stage stage, List<UserTable> selectedUsers) throws  IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mx/uv/fei/gui/registerCourse.fxml"));
        Parent root = loader.load();
        RegisterCourseController controller = loader.getController();
        controller.setSelectedUsers(selectedUsers);
        controller.loadTableUsers();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
     public void showAddStudentsToCourse(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/mx/uv/fei/gui/addStudentsToCourse.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
     
    public void showUsersManager(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/mx/uv/fei/gui/gestiónUsuarios.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch();
    }
}   