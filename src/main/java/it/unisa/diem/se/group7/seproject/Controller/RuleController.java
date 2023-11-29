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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
    private Label titleLabel;

    @FXML
    private ComboBox<ActionType> actionMenu;

    @FXML
    private TextField ruleNameField;

    @FXML
    private ComboBox<TriggerType> triggerMenu;

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

    @FXML
    private Button createRuleButton;

    @FXML
    private Button editRuleButton;

    private Rule ruleBeingEdited;

    //TODO: Refractor initialize method creating a createRuleInit method to improve code readability
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ruleManager = RuleManager.getInstance();
        titleLabel.setText("Create a rule");
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

        editRuleButton.setManaged(false);

    }

    @FXML
    void createNewRule(ActionEvent event) {

        if(validInputs()) {
            //Creation of the Rule

            Rule rule = new Rule(ruleNameField.getText(), createTrigger(triggerMenu.getSelectionModel().getSelectedItem()), createAction(actionMenu.getSelectionModel().getSelectedItem()));
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


    private Trigger createTrigger(TriggerType selectedTrigger) {
        var trigger = switch (selectedTrigger) {
            case TIME_TRIGGER -> new TimeTrigger(hourTimeInput.getValue(), minuteTimeInput.getValue());

            default -> throw new IllegalStateException("Unexpected value: " + selectedTrigger);
        };

        return trigger;
    }

    private Action createAction(ActionType selectedAction) {
        var action = switch (selectedAction) {
            case SHOW_DIALOG_BOX -> new ShowDialogBoxAction(messageActionInput.getText());
            case PLAY_AUDIO -> new PlayAudioAction(selectedFile);

            default -> throw new IllegalStateException("Unexpected value: " + selectedAction);
        };
        return action;
    }
    // TODO: This method should be implemented
    private boolean validInputs() {
        return true;
    }

    @FXML
    public void chooseAudioFileAction(ActionEvent event) {
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

    public void editRuleInit(Rule ruleToEdit) {
        this.ruleBeingEdited = ruleToEdit;
        titleLabel.setText("Edit a rule");
        ruleNameField.setEditable(false);
        ruleNameField.setText(ruleToEdit.getName());
        triggerMenu.setValue(ruleToEdit.getTrigger().getTYPE());
        actionMenu.setValue(ruleToEdit.getAction().getTYPE());
        createRuleButton.setManaged(false);
        editRuleButton.setManaged(true);

    }
    public void editRule(ActionEvent event) {
        if (validInputs()) {
            if (ruleBeingEdited != null) {
                // Update the existing rule with the edited values
                ruleBeingEdited.setName(ruleNameField.getText());
                ruleBeingEdited.setTrigger(createTrigger(triggerMenu.getSelectionModel().getSelectedItem()));
                ruleBeingEdited.setAction(createAction(actionMenu.getSelectionModel().getSelectedItem()));
                closeWindow();
            } else {
                showErrorAlert("ERROR", "No rule is being edited.");
            }
        } else {
            showErrorAlert("ERROR", "You need to fill all the inputs to edit a Rule!");
        }
    }
}

