package mx.uv.fei.logic;


import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Star3oy
 */
public class SSPGER extends Application {
    @Override
    public void start(Stage stage) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("/mx/uv/fei/gui/gestiónUsuarios.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);  
        stage.show();    
    }
    
    public static void main(String[] args) {
        launch();
    }
}   