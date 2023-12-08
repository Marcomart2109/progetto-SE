package it.unisa.diem.se.group7.seproject.Views.ActionViews;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Actions.AppendToFileAction;
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

    public AppendToFileActionView() {
        messageTextField = new TextField();
    }

    @Override
    public Node getView() {
        VBox container = new VBox();

        Label fileLabel = new Label("Select File:");
        fileLabel.setStyle("-fx-font-weight: bold");

        Button chooseFileButton = new Button("Choose File");
        chooseFileButton.setOnAction(event -> openFileChooser());

        HBox fileBox = new HBox(10);
        fileBox.getChildren().addAll(fileLabel, chooseFileButton);

        Label messageLabel = new Label("Enter Message:");
        messageLabel.setStyle("-fx-font-weight: bold");

        HBox messageBox = new HBox(10);
        messageBox.getChildren().addAll(messageLabel, messageTextField);

        container.getChildren().addAll(fileBox, messageBox);

        return container;
    }

    private void openFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");
        selectedFile = fileChooser.showOpenDialog(new Stage());
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
