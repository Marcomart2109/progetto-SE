package it.unisa.diem.se.group7.seproject.Views.ActionViews;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Actions.DeleteFileAction;
import javafx.geometry.Pos;
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
    private Label selectedDirectoryLabel; // Added Label

    public DeleteFileActionView() {
        fileNameTextField = new TextField();
        selectedDirectoryLabel = new Label("No directory selected"); // Default label text
    }

    @Override
    public Node getView() {
        VBox container = new VBox();
        container.setSpacing(INCREASED_SPACING);

        Label fileNameLabel = new Label("Enter File Name:");

        HBox fileNameBox = new HBox(DEFAULT_SPACING);
        fileNameBox.setAlignment(Pos.CENTER_LEFT);
        fileNameBox.getChildren().addAll(fileNameLabel, fileNameTextField);

        Label directoryLabel = new Label("Select Directory:");

        Button chooseDirectoryButton = new Button("Choose Directory");
        chooseDirectoryButton.setOnAction(event -> openDirectoryChooser());

        HBox directoryBox = new HBox(DEFAULT_SPACING);
        directoryBox.setAlignment(Pos.CENTER_LEFT);
        directoryBox.getChildren().addAll(directoryLabel, chooseDirectoryButton);


        container.getChildren().addAll(fileNameBox, directoryBox);

        return container;
    }

    private void openDirectoryChooser() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Directory");
        selectedDirectory = directoryChooser.showDialog(new Stage());

        // Update label text based on whether a directory is selected or not
        if (selectedDirectory != null) {
            selectedDirectoryLabel.setText("Selected directory: " + selectedDirectory.getPath());
        } else {
            selectedDirectoryLabel.setText("No directory selected");
        }
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
