package it.unisa.diem.se.group7.seproject.Model.Actions;


import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.Serializable;

public class ShowDialogBoxAction implements Action, Serializable {
    private String message;
    private final ActionType TYPE = ActionType.SHOW_DIALOG_BOX;


    public ShowDialogBoxAction(String message) {
        this.message = message;
    }

    public ShowDialogBoxAction() {
    }

    public String getMessage() {
        return message;
    }
    @Override
    public ActionType getTYPE() {
        return TYPE;
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

    @Override
    public String toString() {
        return "THEN show an alert displaying the message \"" + message + "\"";
    }
}
