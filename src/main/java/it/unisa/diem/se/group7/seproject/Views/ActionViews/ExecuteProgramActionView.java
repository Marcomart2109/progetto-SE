package it.unisa.diem.se.group7.seproject.Views.ActionViews;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Actions.ExecuteProgramAction;
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

    public ExecuteProgramActionView() {
        argumentsTextField = new TextField();
    }

    @Override
    public Node getView() {
        VBox container = new VBox();

        Label scriptLabel = new Label("Select Script:");
        scriptLabel.setStyle("-fx-font-weight: bold");

        Button chooseScriptButton = new Button("Choose Script");
        chooseScriptButton.setOnAction(event -> openFileChooser());

        HBox scriptBox = new HBox(10);
        scriptBox.getChildren().addAll(scriptLabel, chooseScriptButton);

        Label argumentsLabel = new Label("Enter Command Line Arguments:");
        argumentsLabel.setStyle("-fx-font-weight: bold");

        HBox argumentsBox = new HBox(10);
        argumentsBox.getChildren().addAll(argumentsLabel, argumentsTextField);

        container.getChildren().addAll(scriptBox, argumentsBox);

        return container;
    }

    private void openFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Script");
        selectedScript = fileChooser.showOpenDialog(new Stage());
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
