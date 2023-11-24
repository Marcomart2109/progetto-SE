package it.unisa.diem.se.group7.seproject.Controller;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Actions.ActionType;
import it.unisa.diem.se.group7.seproject.Model.Actions.PlayAudioAction;
import it.unisa.diem.se.group7.seproject.Model.Actions.ShowDialogBoxAction;
import it.unisa.diem.se.group7.seproject.Model.Rules.Rule;
import it.unisa.diem.se.group7.seproject.Model.Rules.RuleManager;
import it.unisa.diem.se.group7.seproject.Model.Triggers.TimeTrigger;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;
import it.unisa.diem.se.group7.seproject.Model.Triggers.TriggerType;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.io.File;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class RuleController implements Initializable {

    private RuleManager ruleManager;

    @FXML
    private ComboBox<ActionType> actionMenu;

    @FXML
    private TextField ruleNameField;

    @FXML
    private ComboBox<TriggerType> triggerMenu;

    private TriggerType selectedTrigger;

    private ActionType selectedAction;

    private File selectedFile;

    @FXML
    private Button selectFileButton;

    @FXML
    private HBox timeTriggerInput;

    @FXML
    private HBox dialogBoxInput;

    @FXML
    private HBox audioFileInput;

    @FXML
    private Spinner<Integer> hourTimeInput;

    @FXML
    private Spinner<Integer> minuteTimeInput;

    @FXML
    private TextField messageActionInput;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ruleManager = RuleManager.getInstance();
        //Initialization of the combo box menus
        triggerMenu.getItems().addAll(TriggerType.values());
        actionMenu.getItems().addAll(ActionType.values());
        //Hidden elements don't occupy space
        timeTriggerInput.managedProperty().bind(timeTriggerInput.visibleProperty());
        dialogBoxInput.managedProperty().bind(dialogBoxInput.visibleProperty());
        audioFileInput.managedProperty().bind(audioFileInput.visibleProperty());

        //Display of the inputs according to user choice in the comboBox menu
        //Triggers
        timeTriggerInput.visibleProperty().bind(triggerMenu.valueProperty().isEqualTo(TriggerType.TIME_TRIGGER));
        //Actions
        dialogBoxInput.visibleProperty().bind(actionMenu.valueProperty().isEqualTo(ActionType.SHOW_DIALOG_BOX));
        audioFileInput.visibleProperty().bind(actionMenu.valueProperty().isEqualTo(ActionType.PLAY_AUDIO));

        //Setup spinner component for time and minutes
        Integer currenthours = LocalTime.now().getHour();
        Integer currentminutes = LocalTime.now().getMinute();

        SpinnerValueFactory<Integer> hourValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23);
        SpinnerValueFactory<Integer> minuteValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59);
        hourValueFactory.setValue(currenthours);
        minuteValueFactory.setValue(currentminutes);

        hourTimeInput.setValueFactory(hourValueFactory);
        minuteTimeInput.setValueFactory(minuteValueFactory);


    }

    @FXML
    void createNewRule(ActionEvent event) {

        if(validInputs()) {
            //Creation of the Rule

            Rule rule = new Rule(ruleNameField.getText(), createTrigger(), createAction());
            ruleManager.addRule(rule);
            closeWindow();

        } else {
            showErrorAlert("ERROR", "You need to fill all the inputs to create a Rule!");
        }
    }

    private void closeWindow() {
        // Get the Stage from any JavaFX Node in the current scene
        Stage stage = (Stage) ruleNameField.getScene().getWindow();
        stage.close();
    }
    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void specifiedAction(ActionEvent event) {
        selectedAction = actionMenu.getSelectionModel().getSelectedItem();
    }

    @FXML
    void specifiedTrigger(ActionEvent event) {
        selectedTrigger = triggerMenu.getSelectionModel().getSelectedItem();
    }

    private Trigger createTrigger() {
        var trigger = switch (selectedTrigger) {
            case TIME_TRIGGER -> new TimeTrigger(hourTimeInput.getValue(), minuteTimeInput.getValue());

            default -> throw new IllegalStateException("Unexpected value: " + selectedTrigger);
        };

        return trigger;
    }

    private Action createAction() {
        var action = switch (selectedAction) {
            case SHOW_DIALOG_BOX -> new ShowDialogBoxAction(messageActionInput.getText());
            case PLAY_AUDIO -> new PlayAudioAction(selectedFile);

            default -> throw new IllegalStateException("Unexpected value: " + selectedAction);
        };
        return action;
    }
    // Temporary implementation
    private boolean validInputs() {
        return !ruleNameField.getText().isBlank() && selectedTrigger != null && selectedAction != null;
    }

    @FXML
    void chooseAudioFileAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Audio File");

        // Set the file extension filters if needed
        // Example: Allow only audio files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Audio Files", "*.mp3", "*.wav", "*.ogg");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show the file chooser dialog
        selectedFile = fileChooser.showOpenDialog(null);
        if(selectedFile != null) {
            selectFileButton.setText("File selected");
        }
    }
}

