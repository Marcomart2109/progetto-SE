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

/**
 * The ExitValueTriggerView class represents a view component for configuring an ExitValueTrigger.
 * It implements the TriggerView interface.
 *
 * <p>
 * The ExitValueTriggerView class displays a user interface that allows the user to select a script file and specify an exit value.
 * It provides methods to get the selected script file and exit value.
 * The class also provides a method to create an ExitValueTrigger based on the selected options.
 * </p>
 *
 * <p>
 * The user interface consists of a file selection button, a spinner for selecting the exit value, and a label to display the selected file.
 * The user can click on the file selection button to open a file chooser dialog and select a script file.
 * The selected file name is then displayed in the label.
 * </p>
 *
 * <p>
 * The ExitValueTriggerView class depends on the FileChooser and Stage classes from the JavaFX library.
 * </p>
 *
 */
public class ExitValueTriggerView implements TriggerView {
    private File selectedScript;
    private final Spinner<Integer> exitValueSpinner;
    private final Label selectedFileLabel;

    public ExitValueTriggerView() {
        exitValueSpinner = new Spinner<>(0, Integer.MAX_VALUE, 0);
        exitValueSpinner.setEditable(true);
        exitValueSpinner.setPrefWidth(60);

        selectedFileLabel = new Label("No file selected");
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

        container.getChildren().addAll(fileBox, spinnerBox);

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
