package sample;

import javax.swing.*;

public interface ConfirmDialgoe {
     static int showConfirmDialoge(String message){
        return JOptionPane.showConfirmDialog(null, message, "Confirm to Next ?", JOptionPane.YES_NO_OPTION);
    }
}
