package it.unisa.diem.se.group7.seproject.Views.ActionViews;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Actions.CopyFileAction;
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

    @Override
    public Node getView() {
        VBox container = new VBox();

        Label sourceFileLabel = new Label("Select Source File:");
        sourceFileLabel.setStyle("-fx-font-weight: bold");

        Button chooseSourceFileButton = new Button("Choose Source File");
        chooseSourceFileButton.setOnAction(event -> openFileChooser());

        HBox sourceFileBox = new HBox(10);
        sourceFileBox.getChildren().addAll(sourceFileLabel, chooseSourceFileButton);

        Label destinationDirectoryLabel = new Label("Select Destination Directory:");
        destinationDirectoryLabel.setStyle("-fx-font-weight: bold");

        Button chooseDestinationDirectoryButton = new Button("Choose Destination Directory");
        chooseDestinationDirectoryButton.setOnAction(event -> openDirectoryChooser());

        HBox destinationDirectoryBox = new HBox(10);
        destinationDirectoryBox.getChildren().addAll(destinationDirectoryLabel, chooseDestinationDirectoryButton);

        container.getChildren().addAll(sourceFileBox, destinationDirectoryBox);

        return container;
    }

    private void openFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");
        selectedSourceFile = fileChooser.showOpenDialog(new Stage());

    }

    private void openDirectoryChooser() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Directory");
        selectedDestinationDirectory = directoryChooser.showDialog(new Stage());
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
