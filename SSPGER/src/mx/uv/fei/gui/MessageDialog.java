package mx.uv.fei.gui;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javax.swing.JOptionPane;

/**
 *
 * @author sue 
 */
public class MessageDialog {
    public static void showSuccessMessage() {
        JOptionPane.showMessageDialog(null, "La información se registró correctamente en el sistema",
                                            "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);        
    }
    
    public static void showErrorDatabaseMessage() {
    JOptionPane.showMessageDialog(null, "No se pudo conectar con la base de datos.\n Inténtelo de nuevo o hágalo más tarde", 
                                             "ERROR", JOptionPane.ERROR_MESSAGE);        
    }

    public static void showInvalidInformationMessage() {
        JOptionPane.showMessageDialog(null, "Los datos ingresados son inválidos.\n Por favor, compruebe la información e inténtelo nuevamente",
                "AVISO", JOptionPane.WARNING_MESSAGE);          
    }
    
    public static void showEmptyFieldsMessage() {
        JOptionPane.showMessageDialog(null, "No se puede dejar ningún campo vacío.\n Por favor, ingrese toda la información solicitada",
                "AVISO", JOptionPane.WARNING_MESSAGE);        
    }

    public static void showCourseAlreadyRegisteredMessage() {
        JOptionPane.showMessageDialog(null, "La información ingresada corresponde a un curso que ya se encuentra registrado en el sistema.\n Por favor, inténtelo nuévamente",
                "AVISO", JOptionPane.INFORMATION_MESSAGE);        
    }
    
    public static void showUserAlreadyRegisteredMessage() {
        JOptionPane.showMessageDialog(null, "La información ingresada corresponde a un usuario que ya se encuentra registrado en el sistema.\n Por favor, inténtelo nuévamente",
                "AVISO", JOptionPane.INFORMATION_MESSAGE);        
    }

    public static boolean showCancelMessage() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setHeaderText(null);
        alert.setTitle("AVISO");
        alert.setContentText("¿Estás seguro que deseas cancelar?");
        Optional<ButtonType> action = alert.showAndWait();
        return action.get().equals(ButtonType.OK); 
    }
    
    public static boolean showDiseableConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setHeaderText(null);
        alert.setTitle("AVISO");
        alert.setContentText("¿Estás seguro que deseas desactivar?");
        Optional<ButtonType> action = alert.showAndWait();
        return action.get().equals(ButtonType.OK); 
    }
}
