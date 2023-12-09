package it.unisa.diem.se.group7.seproject.Views.TriggerViews;

import it.unisa.diem.se.group7.seproject.Model.Triggers.ExitValueTrigger;
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

public class ExitValueTriggerView implements TriggerView {
    private File selectedScript;
    private Spinner<Integer> exitValueSpinner;
    private Label selectedFileLabel; // Added Label

    public ExitValueTriggerView() {
        exitValueSpinner = new Spinner<>(0, Integer.MAX_VALUE, 0);
        exitValueSpinner.setEditable(true);
        exitValueSpinner.setPrefWidth(60);

        selectedFileLabel = new Label("No file selected"); // Default label text
    }

    @Override
    public Node getView() {
        VBox container = new VBox();
        container.setSpacing(INCREASED_SPACING);

        Label label = new Label("Select Script File:");

        Button chooseFileButton = new Button("Choose File");
        chooseFileButton.setOnAction(event -> openFileChooser());

        HBox fileBox = new HBox(DEFAULT_SPACING);
        fileBox.setAlignment(Pos.CENTER_LEFT);
        fileBox.getChildren().addAll(label, chooseFileButton, selectedFileLabel);

        Label exitValueLabel = new Label("Select Exit Value:");

        HBox spinnerBox = new HBox(10);
        spinnerBox.setAlignment(Pos.CENTER_LEFT);
        spinnerBox.getChildren().addAll(exitValueLabel, exitValueSpinner);

        container.getChildren().addAll(fileBox, spinnerBox); // Added Label

        return container;
    }

    private void openFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Script File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Executable Files", "*.exe", "*.bat", "*.sh"));
        selectedScript = fileChooser.showOpenDialog(new Stage());

        // Update label text based on whether a file is selected or not
        if (selectedScript != null) {
            selectedFileLabel.setText("Selected file: " + selectedScript.getName());
        } else {
            selectedFileLabel.setText("No file selected");
        }
    }

    private int getSelectedExitValue() {
        return exitValueSpinner.getValue();
    }

    @Override
    public Trigger getTrigger() {
        return new ExitValueTrigger(selectedScript, getSelectedExitValue());
    }

    @Override
    public boolean isValid() {
        return selectedScript != null;
    }
}
