package it.unisa.diem.se.group7.seproject.Views.TriggerViews;

import it.unisa.diem.se.group7.seproject.Model.Triggers.ExitValueTrigger;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;
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

    public ExitValueTriggerView() {
        exitValueSpinner = new Spinner<>(0, Integer.MAX_VALUE, 0);
    }

    @Override
    public Node getView() {
        VBox container = new VBox();

        Label label = new Label("Select Script File:");
        label.setStyle("-fx-font-weight: bold");

        Button chooseFileButton = new Button("Choose File");
        chooseFileButton.setOnAction(event -> openFileChooser());

        HBox fileBox = new HBox(10);
        fileBox.getChildren().addAll(label, chooseFileButton);

        Label exitValueLabel = new Label("Select Exit Value:");
        exitValueLabel.setStyle("-fx-font-weight: bold");

        HBox spinnerBox = new HBox(10);
        spinnerBox.getChildren().addAll(exitValueLabel, exitValueSpinner);

        container.getChildren().addAll(fileBox, spinnerBox);

        return container;
    }

    private void openFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Script File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Executable Files", "*.exe", "*.bat", "*.sh"));
        selectedScript = fileChooser.showOpenDialog(new Stage());
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
