package it.unisa.diem.se.group7.seproject.Controller;

import it.unisa.diem.se.group7.seproject.Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ruleManager = RuleManager.getInstance();
        //Initialize the combo box menus
        triggerMenu.getItems().addAll(TriggerType.values());
        actionMenu.getItems().addAll(ActionType.values());

    }
    @FXML
    void selectedAction(ActionEvent event) {
        selectedAction = actionMenu.getSelectionModel().getSelectedItem();
        System.out.println("Action: " + selectedAction);

    }

    @FXML
    void selectedTrigger(ActionEvent event) {
        selectedTrigger = triggerMenu.getSelectionModel().getSelectedItem();
        System.out.println("Trigger: " + selectedTrigger);
    }

    @FXML
    void createNewRule(ActionEvent event) {

        if(validInputs()) {
            //Creation of the Rule
            //SimpleTriggerFactory triggerFactory = new SimpleTriggerFactory();
            //SimpleActionFactory actionFactory = new SimpleActionFactory();

            //ruleManager.addRule(new Rule(triggerFactory.create(selectedTrigger), actionFactory.create(selectedAction));

        } else {
            System.out.println("You need to fill all the inputs to create a Rule!");
        }

    }

    boolean validInputs() {
        return !ruleNameField.getText().isBlank() && selectedTrigger != null && selectedAction != null;
    }
}

