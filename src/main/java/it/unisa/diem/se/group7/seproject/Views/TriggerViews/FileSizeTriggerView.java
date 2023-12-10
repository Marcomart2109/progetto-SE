package it.unisa.diem.se.group7.seproject.Views.TriggerViews;

import it.unisa.diem.se.group7.seproject.Model.Triggers.FileSizeTrigger;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * The FileSizeTriggerView class implements the TriggerView interface and represents a view for a file size trigger.
 * <p>
 * This view allows the user to enter a file length in bytes and select a file. It provides a button to open a file chooser dialog and display the selected file name.
 * The view also provides methods to get the entered file length and create a file size trigger based on the entered data.
 *
 */
public class FileSizeTriggerView implements TriggerView {
    private final Spinner<Integer> fileLengthSpinner;
    private File selectedFile;
    private final Label selectedFileLabel;

    public FileSizeTriggerView() {
        fileLengthSpinner = new Spinner<>(0, Integer.MAX_VALUE, 0);
        selectedFileLabel = new Label("No file selected");

        fileLengthSpinner.setEditable(true);
        fileLengthSpinner.setPrefWidth(100);
    }

    @Override
    public Node getView() {
        VBox container = new VBox();
        container.setSpacing(INCREASED_SPACING);

        Label fileLengthLabel = new Label("Enter File Length (in bytes):");

        Button chooseFileButton = new Button("Choose File");
        chooseFileButton.setOnAction(event -> openFileChooser());

        HBox fileLengthBox = new HBox(DEFAULT_SPACING);
        fileLengthBox.setAlignment(Pos.CENTER_LEFT);
        fileLengthBox.getChildren().addAll(fileLengthLabel, fileLengthSpinner);

        HBox fileBox = new HBox(DEFAULT_SPACING);
        fileBox.setAlignment(Pos.CENTER_LEFT);
        fileBox.getChildren().addAll(chooseFileButton, selectedFileLabel);

        container.getChildren().addAll(fileLengthBox, fileBox);

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

    private int getEnteredFileLength() {
        return fileLengthSpinner.getValue();
    }

    @Override
    public Trigger getTrigger() {
        return new FileSizeTrigger(getEnteredFileLength(), selectedFile.getPath());
    }

    @Override
    public boolean isValid() {
        // Check if a file is selected and a file length is entered
        return selectedFile != null && getEnteredFileLength() >= 0;
    }
}
