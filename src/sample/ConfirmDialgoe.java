package sample;

import javafx.scene.control.Alert;

import javax.swing.*;

public interface ConfirmDialgoe {
     static int showConfirmDialoge(String message){
        return JOptionPane.showConfirmDialog(null, message, "Confirm to Next ?", JOptionPane.YES_NO_OPTION);
    }

    static void showAlertDialoge(String title, String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
