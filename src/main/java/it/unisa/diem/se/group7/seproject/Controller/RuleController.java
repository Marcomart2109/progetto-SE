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

/**
 * The RuleController class controls the user interface for creating and editing rules.
 * It handles user input and interacts with the RuleManager to add, edit, and validate rules.
 * RuleController implements the Initializable interface, which provides a method for initializing the controller.
 */
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


    /**
     * Initializes the RuleController by setting up the UI elements and bindings.
     *
     * @param url            the URL of the FXML file
     * @param resourceBundle the ResourceBundle containing localized strings
     */
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
        //Binding to make sure the createRuleButton is not clickable if the inspus are not filled
        createRuleButton.disableProperty().bind(
                triggerTypeMenu.valueProperty().isNull()
                        .or(actionTypeMenu.valueProperty().isNull())
                        .or(onceActivationCheckbox.selectedProperty().not().and(twiceActivationCheckbox.selectedProperty().not()))
        );

        triggersBox.setFillWidth(true);
        actionsBox.setFillWidth(true);

    }

    /**
     * Clears the triggersBox container and handles the selection of the trigger type from the triggerTypeMenu.
     * If the selected trigger type is "ELEMENTARY", it creates an instance of ElementaryTriggerView and adds its view to the triggersBox container.
     * If the selected trigger type is "COMPOSITE", it creates an instance of CompositeTriggerView and adds its view to the triggersBox container.
     */
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
    /**
     * Clears the actionsBox container and handles the selection of the action type from the actionTypeMenu.
     * If the selected action type is "SINGLE", it creates a new SingleActionView and adds its view to the actionsBox container.
     * If the selected action type is "SEQUENCE", it creates a new SequenceActionView and adds its view to the actionsBox container.
     */
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

    /**
     * Sets up the date spinner by creating and setting the value factories for the day, hour, and minute spinners.
     * The day spinner allows values from 0 to 99, the hour spinner allows values from 0 to 23, and the minute spinner allows values from 0 to 59.
     * Then, the value factories are set for the sleepingDaySpinner, sleepingHourSpinner, and sleepingMinuteSpinner.
     *
     */
    private void setUpDateSpinner() {
        SpinnerValueFactory<Integer> dayValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99);
        SpinnerValueFactory<Integer> hourValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        SpinnerValueFactory<Integer> minuteValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);

        sleepingDaySpinner.setValueFactory(dayValueFactory);
        sleepingHourSpinner.setValueFactory(hourValueFactory);
        sleepingMinuteSpinner.setValueFactory(minuteValueFactory);
    }


    /**
     * Creates a new rule based on user inputs and adds it to the rule manager.
     * If the inputs are valid and at least one checkbox is selected, a new rule is created and added to the rule manager.
     * The rule can be a SimpleRule or a RuleSleepDecorator based on the selected checkboxes and user inputs.
     * If the inputs are not valid or no checkbox is selected, an error alert is shown.
     *
     * @param event the action event
     */
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
            showErrorAlert();
        }
    }

    /**
     * Checks if the inputs for creating a new rule are valid.
     * The inputs are considered valid if all of the following conditions are met:
     * - The currentActionView is valid (returns true when calling the isValid() method)
     * - The currentTriggerView is valid (returns true when calling the isValid() method)
     * - The ruleNameField text is not empty
     *
     * @return true if the inputs are valid, false otherwise
     */
    private boolean areInputsValid() {
        return currentActionView.isValid() && currentTriggerView.isValid() && !ruleNameField.getText().isEmpty();
    }

    /**
     * Checks if at least one checkbox is selected.
     *
     * @return true if at least one checkbox is selected, false otherwise
     */
    private boolean isAtLeastOneCheckboxSelected() {
        return onceActivationCheckbox.isSelected() || twiceActivationCheckbox.isSelected();
    }

    /**
     * Closes the current window.
     */
    private void closeWindow() {
        // Get the Stage from any JavaFX Node in the current scene
        Stage stage = (Stage) ruleNameField.getScene().getWindow();
        stage.close();
    }
    /**
     * Displays an error alert dialog with a specific title, message, and alert type.
     */
    private void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Inputs");
        alert.setHeaderText(null);
        alert.setContentText("Please make sure all inputs are valid.");
        alert.showAndWait();
    }

    /**
     * Initializes the rule editing process by setting the rule to be edited,
     * updating the title label, and disabling certain UI elements.
     *
     * @param ruleToEdit the rule to be edited
     */
    public void editRuleInit(Rule ruleToEdit) {
        this.ruleBeingEdited = ruleToEdit;
        titleLabel.setText("Edit a rule");
        ruleNameField.setDisable(true);
        ruleNameField.setText(ruleToEdit.getName());
        activationBoxInput.setDisable(true);
        createRuleButton.setManaged(false);
        editRuleButton.setManaged(true);

    }

    /**
     * Edits the currently selected rule by updating its trigger, action, and active status.
     * If the inputs are valid, the trigger and action of the rule being edited are updated
     * based on the currentTriggerView and currentActionView. The active status of the rule
     * is set to true, and a message is printed to the console displaying the updated rule.
     * The window is closed after the rule is edited.
     * If the inputs are not valid, an error alert is shown.
     *
     * @param event the action event that triggered the method
     */
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
            showErrorAlert();
        }
    }




}

