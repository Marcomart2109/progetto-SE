package it.unisa.diem.se.group7.seproject.Controller;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Actions.ActionType;
import it.unisa.diem.se.group7.seproject.Model.Actions.ShowDialogBoxAction;
import it.unisa.diem.se.group7.seproject.Model.Rules.Rule;
import it.unisa.diem.se.group7.seproject.Model.Rules.RuleManager;
import it.unisa.diem.se.group7.seproject.Model.Triggers.TimeTrigger;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;
import it.unisa.diem.se.group7.seproject.Model.Triggers.TriggerType;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RuleController implements Initializable {

    RuleManager ruleManager;

    @FXML
    private ComboBox<ActionType> actionMenu;

    @FXML
    private TextField ruleNameField;

    @FXML
    private ComboBox<TriggerType> triggerMenu;

    private TriggerType selectedTrigger;

    private ActionType selectedAction;

    @FXML
    private HBox timeTriggerInput;
    @FXML
    private HBox dialogBoxInput;

    @FXML
    private TextField hourTimeInput;

    @FXML
    private TextField minuteTimeInput;

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

        //Display of the inputs according to user choice in the comboBox menu
        timeTriggerInput.visibleProperty().bind(triggerMenu.valueProperty().isEqualTo(TriggerType.TIME_TRIGGER));
        dialogBoxInput.visibleProperty().bind(actionMenu.valueProperty().isEqualTo(ActionType.SHOW_DIALOG_BOX));

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
            case TIME_TRIGGER -> new TimeTrigger(Integer.parseInt(hourTimeInput.getText()), Integer.parseInt(minuteTimeInput.getText()));

            default -> throw new IllegalStateException("Unexpected value: " + selectedTrigger);
        };

        return trigger;
    }

    private Action createAction() {
        var action = switch (selectedAction) {
            case SHOW_DIALOG_BOX -> new ShowDialogBoxAction(messageActionInput.getText());

            default -> throw new IllegalStateException("Unexpected value: " + selectedAction);
        };
        return action;
    }
    // Temporary implementation
    boolean validInputs() {
        if (!ruleNameField.getText().isBlank() && selectedTrigger != null && selectedAction != null) {
            return !hourTimeInput.getText().isEmpty() && !minuteTimeInput.getText().isEmpty() && !messageActionInput.getText().isEmpty();
        }
        return false;
    }
}

