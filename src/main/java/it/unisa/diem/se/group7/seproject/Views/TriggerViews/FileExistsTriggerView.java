package it.unisa.diem.se.group7.seproject.Views.TriggerViews;

import it.unisa.diem.se.group7.seproject.Model.Triggers.FileExistsTrigger;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;
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

/**
 * FileExistsTriggerView implements the TriggerView interface and represents a view for a file exists trigger.
 *
 * <p> The view allows the user to enter a file name and select a directory. The trigger is considered valid
 * if both the file name and directory are specified.
 *
 * <p> The view provides a text field to enter the file name and a button to open a directory chooser dialog
 * to select the directory. The selected directory is displayed in a label.
 *
 */
public class FileExistsTriggerView implements TriggerView {
    private final TextField fileNameTextField;
    private File selectedDirectory;
    private final Label selectedDirectoryLabel; // Added Label

    public FileExistsTriggerView() {
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

        HBox directoryBox = new HBox(10);
        directoryBox.setAlignment(Pos.CENTER_LEFT);
        directoryBox.getChildren().addAll(directoryLabel, chooseDirectoryButton, selectedDirectoryLabel); // Added Label

        container.getChildren().addAll(fileNameBox, directoryBox);

        return container;
    }

    private void openDirectoryChooser() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Directory");
        selectedDirectory = directoryChooser.showDialog(new Stage());

        // Update label text based on whether a directory is selected or not
        if (selectedDirectory != null) {
            selectedDirectoryLabel.setText("Selected directory: " + selectedDirectory.getAbsolutePath());
        } else {
            selectedDirectoryLabel.setText("No directory selected");
        }
    }

    private String getEnteredFileName() {
        return fileNameTextField.getText().trim();
    }

    @Override
    public Trigger getTrigger() {
        return new FileExistsTrigger(getEnteredFileName(), selectedDirectory);
    }

    @Override
    public boolean isValid() {
        String enteredFileName = getEnteredFileName();
        return !enteredFileName.isEmpty() && selectedDirectory != null;
    }
}
