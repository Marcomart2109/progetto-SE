package it.unisa.diem.se.group7.seproject.Controller;

import it.unisa.diem.se.group7.seproject.Model.Rules.Rule;
import it.unisa.diem.se.group7.seproject.Model.Rules.RuleManager;
import it.unisa.diem.se.group7.seproject.Model.Rules.RuleSleepDecorator;
import it.unisa.diem.se.group7.seproject.Model.Rules.SimpleRule;
import it.unisa.diem.se.group7.seproject.Views.ActionViews.ActionView;
import it.unisa.diem.se.group7.seproject.Views.TriggerViews.CompositeTriggerView;
import it.unisa.diem.se.group7.seproject.Views.TriggerViews.ElementaryTriggerView;
import it.unisa.diem.se.group7.seproject.Views.ActionViews.SequenceActionView;
import it.unisa.diem.se.group7.seproject.Views.ActionViews.SingleActionView;
import it.unisa.diem.se.group7.seproject.Views.TriggerViews.TriggerView;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RuleController implements Initializable {
    @FXML
    public Spinner<Integer> sleepingDaySpinner;

    @FXML
    public Spinner<Integer> sleepingHourSpinner;

    @FXML
    public Spinner<Integer> sleepingMinuteSpinner;

    @FXML
    public VBox sleepingBoxInput;

    @FXML
    public CheckBox onceActivationCheckbox;

    @FXML
    public CheckBox twiceActivationCheckbox;

    @FXML
    public VBox activationBoxInput;

    @FXML
    public VBox triggersBox;

    @FXML
    public VBox actionsBox;

    @FXML
    private Label titleLabel;

    @FXML
    private TextField ruleNameField;

    @FXML
    private Button createRuleButton;

    @FXML
    private Button editRuleButton;

    @FXML
    private ComboBox<String> triggerTypeMenu;

    @FXML
    private ComboBox<String> actionTypeMenu;

    private RuleManager ruleManager;

    private Rule ruleBeingEdited;

    private TriggerView currentTriggerView;

    private ActionView currentActionView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ruleManager = RuleManager.getInstance();
        titleLabel.setText("Create a rule");

        setUpDateSpinner();


        //Bindings for Activation checkboxes
        onceActivationCheckbox.disableProperty().bind(twiceActivationCheckbox.selectedProperty());
        twiceActivationCheckbox.disableProperty().bind(onceActivationCheckbox.selectedProperty());
        //Bindings for sleeping time display when the user select the right checkbox
        sleepingBoxInput.visibleProperty().bind(sleepingBoxInput.managedProperty());
        sleepingBoxInput.managedProperty().bind(twiceActivationCheckbox.selectedProperty());

        editRuleButton.setManaged(false);

        triggerTypeMenu.setItems(FXCollections.observableArrayList("ELEMENTARY", "COMPOSITE"));
        actionTypeMenu.setItems(FXCollections.observableArrayList("SINGLE", "SEQUENCE"));

        triggerTypeMenu.setOnAction(actionEvent -> handleTriggerTypeSelection());
        actionTypeMenu.setOnAction(actionEvent -> handleActionTypeSelection());

        triggersBox.setFillWidth(true);
        actionsBox.setFillWidth(true);

    }

    private void handleTriggerTypeSelection() {
        triggersBox.getChildren().clear();

        if (triggerTypeMenu.getValue().equals("ELEMENTARY")) {
            currentTriggerView = new ElementaryTriggerView();
            triggersBox.getChildren().add(currentTriggerView.getView());
        } else if (triggerTypeMenu.getValue().equals("COMPOSITE")) {
            currentTriggerView = new CompositeTriggerView();
            triggersBox.getChildren().add(currentTriggerView.getView());
        }
    }
    private void handleActionTypeSelection() {
        actionsBox.getChildren().clear();

        if (actionTypeMenu.getValue().equals("SINGLE")) {
            currentActionView = new SingleActionView();
            actionsBox.getChildren().add(currentActionView.getView());
        } else if (actionTypeMenu.getValue().equals("SEQUENCE")) {
            currentActionView = new SequenceActionView();
            actionsBox.getChildren().add(currentActionView.getView());
        }
    }

    private void setUpDateSpinner() {
        SpinnerValueFactory<Integer> dayValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99);
        SpinnerValueFactory<Integer> hourValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        SpinnerValueFactory<Integer> minuteValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);

        sleepingDaySpinner.setValueFactory(dayValueFactory);
        sleepingHourSpinner.setValueFactory(hourValueFactory);
        sleepingMinuteSpinner.setValueFactory(minuteValueFactory);
    }


    @FXML
    void createNewRule(ActionEvent event) {
        Rule rule = null;
        if(areInputsValid() && isAtLeastOneCheckboxSelected()) {
            if(onceActivationCheckbox.isSelected()) {
                rule = new SimpleRule(ruleNameField.getText(), currentTriggerView.getTrigger(), currentActionView.getAction());
            }
            if(twiceActivationCheckbox.isSelected()) {
                rule = new RuleSleepDecorator(new SimpleRule(ruleNameField.getText(), currentTriggerView.getTrigger(), currentActionView.getAction()),
                        sleepingDaySpinner.getValue(), sleepingHourSpinner.getValue(), sleepingMinuteSpinner.getValue());
            }
            ruleManager.addRule(rule);
            closeWindow();
        } else {
            showErrorAlert("Invalid Inputs", "Please make sure all inputs are valid.");
        }
    }

    private boolean areInputsValid() {
        return currentActionView.isValid() && currentTriggerView.isValid() && !ruleNameField.getText().isEmpty();
    }

    private boolean isAtLeastOneCheckboxSelected() {
        return onceActivationCheckbox.isSelected() || twiceActivationCheckbox.isSelected();
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



    public void editRuleInit(Rule ruleToEdit) {
        this.ruleBeingEdited = ruleToEdit;
        titleLabel.setText("Edit a rule");
        ruleNameField.setDisable(true);
        ruleNameField.setText(ruleToEdit.getName());
        activationBoxInput.setDisable(true);
        createRuleButton.setManaged(false);
        editRuleButton.setManaged(true);

    }

    @FXML
    public void editRule(ActionEvent event) {
        if (areInputsValid()) {
            ruleBeingEdited.setTrigger(currentTriggerView.getTrigger());
            ruleBeingEdited.setAction(currentActionView.getAction());
            ruleBeingEdited.setActive(true);
            System.out.println("Rule edited:\nTrigger: " + ruleBeingEdited.getTrigger() + "\nAction: " + ruleBeingEdited.getAction() +
                    "\nActive status: " + ruleBeingEdited.isActive());
            closeWindow();
        } else {
            showErrorAlert("Invalid Inputs", "Please make sure all inputs are valid.");
        }
    }




}

