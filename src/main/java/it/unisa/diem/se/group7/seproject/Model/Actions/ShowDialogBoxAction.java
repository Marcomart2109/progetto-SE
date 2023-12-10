package it.unisa.diem.se.group7.seproject.Model.Actions;


import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.Serializable;
/**
 * The ShowDialogBoxAction class serves to display alert dialog boxes to the user. The class implements Serializable
 * and Action interfaces.
 *
 * <p>Example of creating an instance of ShowDialogBoxAction and displaying an alert box:
 * <pre>
 *   ShowDialogBoxAction showDialogBoxAction = new ShowDialogBoxAction("This is a message");
 *   showDialogBoxAction.execute();
 * </pre>
 *
 */
public class ShowDialogBoxAction implements Action, Serializable {
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

    @Override
    public String toString() {
        return "show an alert displaying the message \"" + message + "\"";
    }
}
