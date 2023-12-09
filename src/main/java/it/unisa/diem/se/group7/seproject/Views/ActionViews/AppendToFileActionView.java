package it.unisa.diem.se.group7.seproject.Views.ActionViews;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Actions.AppendToFileAction;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class AppendToFileActionView implements ActionView {
    private File selectedFile;
    private TextField messageTextField;
    private Label selectedFileLabel; // Added Label

    public AppendToFileActionView() {
        messageTextField = new TextField();
        selectedFileLabel = new Label("No file selected"); // Default label text
    }

    @Override
    public Node getView() {
        VBox container = new VBox();
        container.setSpacing(INCREASED_SPACING);

        Label fileLabel = new Label("Select File:");

        Button chooseFileButton = new Button("Choose File");
        chooseFileButton.setOnAction(event -> openFileChooser());

        HBox fileBox = new HBox(DEFAULT_SPACING);
        fileBox.setAlignment(Pos.CENTER_LEFT);
        fileBox.getChildren().addAll(fileLabel, chooseFileButton, selectedFileLabel);

        Label messageLabel = new Label("Enter Message:");

        HBox messageBox = new HBox(DEFAULT_SPACING);
        messageBox.setAlignment(Pos.CENTER_LEFT);
        messageBox.getChildren().addAll(messageLabel, messageTextField);

        container.getChildren().addAll(fileBox, messageBox);

        return container;
    }

    private void openFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");
        selectedFile = fileChooser.showOpenDialog(new Stage());

        // Update label text based on whether a file is selected or not
        if (selectedFile != null) {
            selectedFileLabel.setText("Selected file: " + selectedFile.getName());
        } else {
            selectedFileLabel.setText("No file selected");
        }
    }

    private String getEnteredMessage() {
        return messageTextField.getText().trim();
    }

    @Override
    public Action getAction() {
        return new AppendToFileAction(selectedFile, getEnteredMessage());
    }

    @Override
    public boolean isValid() {
        // Check if a file is selected and a message is entered
        return selectedFile != null && !getEnteredMessage().isEmpty();
    }
}
