package it.unisa.diem.se.group7.seproject.Model;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ShowDialogBoxAction implements Action{
    private String message;


    public ShowDialogBoxAction(String message) {
        this.message = message;
    }

    public ShowDialogBoxAction() {
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void execute() {
        // Create a confirmation dialog
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert!");
            alert.setHeaderText(null);
            // Set alert to show user message
            alert.setContentText(message);
            // Show the alert
            alert.show();
        });
    }
}
