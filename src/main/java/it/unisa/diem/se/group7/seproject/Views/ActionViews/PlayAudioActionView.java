package it.unisa.diem.se.group7.seproject.Views.ActionViews;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Actions.PlayAudioAction;
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

    @Override
    public Node getView() {
        VBox container = new VBox();

        Label label = new Label("Select Audio File:");
        label.setStyle("-fx-font-weight: bold");

        Button chooseFileButton = new Button("Choose File");
        chooseFileButton.setOnAction(event -> openFileChooser());

        HBox fileBox = new HBox(10);
        fileBox.getChildren().addAll(label, chooseFileButton);

        container.getChildren().addAll(fileBox);

        return container;
    }

    private void openFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Audio File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Audio Files", "*.mp3", "*.wav", "*.ogg"));
        selectedAudioFile = fileChooser.showOpenDialog(new Stage());
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
