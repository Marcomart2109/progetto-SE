package it.unisa.diem.se.group7.seproject.Views.ActionViews;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Actions.CopyFileAction;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class CopyFileActionView implements ActionView {
    private File selectedSourceFile;
    private File selectedDestinationDirectory;
    private Label selectedSourceFileLabel; // Added Label
    private Label selectedDestinationDirectoryLabel; // Added Label

    public CopyFileActionView() {
        selectedSourceFileLabel = new Label("No source file selected"); // Default label text
        selectedDestinationDirectoryLabel = new Label("No destination directory selected"); // Default label text
    }

    @Override
    public Node getView() {
        VBox container = new VBox();
        container.setSpacing(INCREASED_SPACING);

        Label sourceFileLabel = new Label("Select Source File:");

        Button chooseSourceFileButton = new Button("Choose Source File");
        chooseSourceFileButton.setOnAction(event -> openFileChooser());

        HBox sourceFileBox = new HBox(DEFAULT_SPACING);
        sourceFileBox.setAlignment(Pos.CENTER_LEFT);
        sourceFileBox.getChildren().addAll(sourceFileLabel, chooseSourceFileButton, selectedSourceFileLabel); // Added Label

        Label destinationDirectoryLabel = new Label("Select Destination Directory:");

        Button chooseDestinationDirectoryButton = new Button("Choose Destination Directory");
        chooseDestinationDirectoryButton.setOnAction(event -> openDirectoryChooser());

        HBox destinationDirectoryBox = new HBox(DEFAULT_SPACING);
        destinationDirectoryBox.setAlignment(Pos.CENTER_LEFT);
        destinationDirectoryBox.getChildren().addAll(destinationDirectoryLabel, chooseDestinationDirectoryButton, selectedDestinationDirectoryLabel); // Added Label

        container.getChildren().addAll(sourceFileBox, destinationDirectoryBox);

        return container;
    }

    private void openFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");
        selectedSourceFile = fileChooser.showOpenDialog(new Stage());

        // Update label text based on whether a source file is selected or not
        if (selectedSourceFile != null) {
            selectedSourceFileLabel.setText("Selected source file: " + selectedSourceFile.getName());
        } else {
            selectedSourceFileLabel.setText("No source file selected");
        }
    }

    private void openDirectoryChooser() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Directory");
        selectedDestinationDirectory = directoryChooser.showDialog(new Stage());

        // Update label text based on whether a destination directory is selected or not
        if (selectedDestinationDirectory != null) {
            selectedDestinationDirectoryLabel.setText("Selected destination directory: " + selectedDestinationDirectory.getPath());
        } else {
            selectedDestinationDirectoryLabel.setText("No destination directory selected");
        }
    }

    @Override
    public Action getAction() {
        return new CopyFileAction(selectedDestinationDirectory, selectedSourceFile);
    }

    @Override
    public boolean isValid() {
        // Check if both a source file and a destination directory are selected
        return selectedSourceFile != null && selectedDestinationDirectory != null;
    }
}
