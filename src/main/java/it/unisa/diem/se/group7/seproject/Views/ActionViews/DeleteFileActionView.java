package it.unisa.diem.se.group7.seproject.Views.ActionViews;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Actions.DeleteFileAction;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class DeleteFileActionView implements ActionView {
    private TextField fileNameTextField;
    private File selectedDirectory;

    public DeleteFileActionView() {
        fileNameTextField = new TextField();
    }

    @Override
    public Node getView() {
        VBox container = new VBox();

        Label fileNameLabel = new Label("Enter File Name:");
        fileNameLabel.setStyle("-fx-font-weight: bold");

        HBox fileNameBox = new HBox(10);
        fileNameBox.getChildren().addAll(fileNameLabel, fileNameTextField);

        Label directoryLabel = new Label("Select Directory:");
        directoryLabel.setStyle("-fx-font-weight: bold");

        Button chooseDirectoryButton = new Button("Choose Directory");
        chooseDirectoryButton.setOnAction(event -> openDirectoryChooser());

        HBox directoryBox = new HBox(10);
        directoryBox.getChildren().addAll(directoryLabel, chooseDirectoryButton);

        container.getChildren().addAll(fileNameBox, directoryBox);

        return container;
    }

    private void openDirectoryChooser() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Directory");
        selectedDirectory = directoryChooser.showDialog(new Stage());
    }

    private String getEnteredFileName() {
        return fileNameTextField.getText().trim();
    }

    @Override
    public Action getAction() {
        return new DeleteFileAction(getEnteredFileName(), selectedDirectory);
    }

    @Override
    public boolean isValid() {
        // Check if a file name is entered and a directory is selected
        return !getEnteredFileName().isEmpty() && selectedDirectory != null;
    }
}
