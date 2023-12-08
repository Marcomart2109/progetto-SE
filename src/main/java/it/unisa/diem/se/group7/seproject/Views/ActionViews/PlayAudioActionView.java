package it.unisa.diem.se.group7.seproject.Views.ActionViews;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Actions.PlayAudioAction;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class PlayAudioActionView implements ActionView {
    private File selectedAudioFile;
    private Label selectedFileLabel; // Added Label

    public PlayAudioActionView() {
        selectedFileLabel = new Label("No audio file selected"); // Default label text
    }

    @Override
    public Node getView() {
        VBox container = new VBox();
        container.setSpacing(DEFAULT_SPACING);

        Label label = new Label("Select Audio File:");

        Button chooseFileButton = new Button("Choose File");
        chooseFileButton.setOnAction(event -> openFileChooser());

        HBox fileBox = new HBox(DEFAULT_SPACING);
        fileBox.setAlignment(Pos.CENTER_LEFT);
        fileBox.getChildren().addAll(chooseFileButton, selectedFileLabel); // Added Label

        container.getChildren().addAll(label, fileBox);

        return container;
    }

    private void openFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Audio File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Audio Files", "*.mp3", "*.wav", "*.ogg"));
        selectedAudioFile = fileChooser.showOpenDialog(new Stage());

        // Update label text based on whether an audio file is selected or not
        if (selectedAudioFile != null) {
            selectedFileLabel.setText("Selected audio file: " + selectedAudioFile.getName());
        } else {
            selectedFileLabel.setText("No audio file selected");
        }
    }

    @Override
    public Action getAction() {
        return new PlayAudioAction(selectedAudioFile);
    }

    @Override
    public boolean isValid() {
        // Check if an audio file is selected
        return selectedAudioFile != null;
    }
}
