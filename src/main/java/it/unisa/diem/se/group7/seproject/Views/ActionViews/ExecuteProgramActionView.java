package it.unisa.diem.se.group7.seproject.Views.ActionViews;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Actions.ExecuteProgramAction;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ExecuteProgramActionView implements ActionView {
    private File selectedScript;
    private TextField argumentsTextField;
    private Label selectedScriptLabel; // Added Label

    public ExecuteProgramActionView() {
        argumentsTextField = new TextField();
        selectedScriptLabel = new Label("No script selected"); // Default label text
    }

    @Override
    public Node getView() {
        VBox container = new VBox();
        container.setSpacing(INCREASED_SPACING);

        Label scriptLabel = new Label("Select Script:");

        Button chooseScriptButton = new Button("Choose Script");
        chooseScriptButton.setOnAction(event -> openFileChooser());

        HBox scriptBox = new HBox(DEFAULT_SPACING);
        scriptBox.setAlignment(Pos.CENTER_LEFT);
        scriptBox.getChildren().addAll(scriptLabel, chooseScriptButton, selectedScriptLabel);

        Label argumentsLabel = new Label("Enter Command Line Arguments:");

        HBox argumentsBox = new HBox(DEFAULT_SPACING);
        argumentsBox.setAlignment(Pos.CENTER_LEFT);
        argumentsBox.getChildren().addAll(argumentsLabel, argumentsTextField);

        container.getChildren().addAll(scriptBox, argumentsBox);

        return container;
    }

    private void openFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Script");
        selectedScript = fileChooser.showOpenDialog(new Stage());

        // Update label text based on whether a script is selected or not
        if (selectedScript != null) {
            selectedScriptLabel.setText("Selected script: " + selectedScript.getName());
        } else {
            selectedScriptLabel.setText("No script selected");
        }
    }

    private String getEnteredArguments() {
        return argumentsTextField.getText().trim();
    }

    @Override
    public Action getAction() {
        return new ExecuteProgramAction(selectedScript, getEnteredArguments());
    }

    @Override
    public boolean isValid() {
        // Check if a script is selected
        return selectedScript != null;
    }
}
