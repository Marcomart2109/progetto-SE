package it.unisa.diem.se.group7.seproject.Controller;

import it.unisa.diem.se.group7.seproject.Application;
import it.unisa.diem.se.group7.seproject.Model.Rules.Rule;
import it.unisa.diem.se.group7.seproject.Model.Rules.RuleBackupManager;
import it.unisa.diem.se.group7.seproject.Model.Rules.RuleManager;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The MainController class handles the main functionality of the application's main view.
 * It initializes and manages the TableView that displays a list of Rule objects,
 * and includes methods for adding, editing, and deleting rules.
 */
public class MainController implements Initializable {

    @FXML
    private TableColumn<Rule, Integer> indexClm;

    @FXML
    private TableColumn<Rule, String> rulesClm;

    @FXML
    private TableColumn<Rule, Boolean> statusClm;

    @FXML
    private TableView<Rule> tableView;

    @FXML
    private MenuBar menuBar;

    @FXML
    private VBox detailBox;

    @FXML
    private Label ruleNameLabel;

    @FXML
    private TextArea triggerDetailText;

    @FXML
    private TextArea actionDetailText;

    @FXML
    private Label selectedRuleLabel;

    private ObservableList<Rule> rules;

    private RuleManager ruleManager;

    private RuleBackupManager rbm;

    /**
     * Initializes the MainController by setting up the necessary components and loading data.
     *
     * @param url             The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle  The resource bundle that contains the localized strings for the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ruleManager = RuleManager.getInstance();
        rules = ruleManager.getRules();

        // Load data from the binary file to the rules ObservableList
        rbm = RuleBackupManager.getInstance();
        rbm.loadFromBinaryFile(rules);

        initTableView();
        initDetailBox();
        initBottomBar();
    }

    /**
     * Initializes the TableView component with appropriate cell factories and event handlers.
     * This method is called during the initialization of the MainController class.
     * It sets up the columns, cell factories, and event handlers for the TableView.
     * Additionally, it allows multiple selection on the TableView and binds the items to the rules ObservableList.
     */
    private void initTableView() {
        rulesClm.setCellValueFactory(new PropertyValueFactory<>("name"));
        indexClm.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(rules.indexOf(cellData.getValue()) + 1));
        rulesClm.setCellFactory(TextFieldTableCell.forTableColumn());
        rulesClm.setOnEditCommit(event -> {
            Rule rule = event.getRowValue();
            rule.setName(event.getNewValue());
        });

        statusClm.setCellValueFactory(cellData -> cellData.getValue().activeProperty());
        statusClm.setCellFactory(column -> new TableCell<>() {
            private final Circle circle = new Circle(5);

            @Override
            protected void updateItem(Boolean active, boolean empty) {
                super.updateItem(active, empty);

                if (empty || active == null) {
                    setGraphic(null);
                } else {
                    circle.setFill(active ? Color.GREEN : Color.RED);
                    setGraphic(circle);
                }
            }
        });


        //Allow multiple selection on tableView
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.setItems(rules);

    }

    /**
     * Binds the visibility of the detail box to whether an item is selected.
     * Also updates the labels in the detail box with the selected rule's details.
     * If no rule is selected, clears the labels in the detail box.
     */
    private void initDetailBox() {
        // Bind the visibility of the detail box to whether an item is selected
        detailBox.visibleProperty().bind(Bindings.isNotEmpty(tableView.getSelectionModel().getSelectedItems()));

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Update the labels in the detail box
                ruleNameLabel.setText(newValue.getName());
                triggerDetailText.setText(newValue.getTrigger().toString());
                actionDetailText.setText(newValue.getAction().toString());

                // Update the rulesClm label directly

            } else {
                // Clear labels if no rule is selected
                ruleNameLabel.setText("");
                triggerDetailText.setText("");
                actionDetailText.setText("");
            }
        });
    }

    /**
     * Initializes the bottom bar component.
     * This method binds the text property of the selectedRuleLabel with a StringExpression based on the number of selected items in the tableView.
     * If at least one item is selected, it sets the text to "{selectedItemsCount} selected {rules/rule}", depending on the number of selected items.
     * If no item is selected, it clears the text of the selectedRuleLabel.
     * Additionally, it binds the visibility property of the selectedRuleLabel with the isEmpty property of the selectedItemsText.
     */
    private void initBottomBar() {
        StringExpression selectedItemsText = Bindings.createStringBinding(() -> {
            int selectedItemsCount = tableView.getSelectionModel().getSelectedItems().size();

            if (selectedItemsCount > 0) {
                return String.format("%d selected %s", selectedItemsCount, selectedItemsCount > 1 ? "rules" : "rule");
            } else {
                return "";
            }
        }, tableView.getSelectionModel().getSelectedItems());

        selectedRuleLabel.textProperty().bind(selectedItemsText);
        selectedRuleLabel.visibleProperty().bind(Bindings.isNotEmpty(selectedItemsText));
    }

    /**
     * Opens a new window for adding a new rule.
     * This method loads the FXML file for the new view, creates a new stage for the view,
     * and sets the new scene on the stage.
     * It also sets the title of the stage, adds a CSS stylesheet to the scene,
     * and shows the new stage.
     * This method is associated with a button click event in the main view.
     *
     */
    @FXML
    public void addNewRule() {
        try {
            // Load the FXML file for the new view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/unisa/diem/se/group7/seproject/rule-view.fxml"));
            Parent root = loader.load();

            // Create a new stage for the new view
            Stage newStage = new Stage();
            newStage.initStyle(StageStyle.UTILITY);
            newStage.initModality(Modality.APPLICATION_MODAL); // Block events to other windows
            newStage.setTitle("Desktop RuleMaster - Create a new rule");

            // Set the new scene on the stage
            Scene scene = new Scene(root);
            newStage.setScene(scene);
            String cssPath = Objects.requireNonNull(Application.class.getResource("style/style.css")).toExternalForm();
            scene.getStylesheets().add(cssPath);

            // Show the new stage
            newStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Opens a new window for editing a selected rule.
     * This method loads the FXML file for the new view, creates a new stage for the view,
     * and sets the new scene on the stage. It also sets the title of the stage, adds a CSS stylesheet to the scene,
     * and shows the new stage. This method is associated with an action event, typically triggered by a button click.
     *
     * @param event The action event that triggers the method.
     */
    @FXML
    public void editRuleAction(ActionEvent event) {
        ObservableList<Rule> selectedItems = tableView.getSelectionModel().getSelectedItems();

        if (selectedItems.size() == 1) {
            Rule selectedItem = selectedItems.getFirst();
            try {
                // Load the FXML file for the new view
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/unisa/diem/se/group7/seproject/rule-view.fxml"));
                Parent root = loader.load();

                RuleController ruleController = loader.getController();
                ruleController.editRuleInit(selectedItem);

                // Create a new stage for the new view
                Stage newStage = new Stage();
                newStage.initStyle(StageStyle.UTILITY);
                newStage.initModality(Modality.APPLICATION_MODAL); // Block events to other windows
                newStage.setTitle("Desktop RuleMaster - Edit an existing rule");

                // Set the new scene on the stage
                Scene scene = new Scene(root);
                newStage.setScene(scene);
                String cssPath = Objects.requireNonNull(Application.class.getResource("style/style.css")).toExternalForm();
                scene.getStylesheets().add(cssPath);

                // Show the new stage
                newStage.showAndWait();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Display an error message if more than one item is selected
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Multiple Items Selected");
            alert.setContentText("Please select only one rule to edit.");
            alert.showAndWait();
        }
    }
    /**
     * Deletes the selected rule(s) from the TableView.
     *
     * @param event the ActionEvent triggered by the delete button
     */
    @FXML
    public void deleteRuleAction(ActionEvent event) {
        ObservableList<Rule> selectedItems = tableView.getSelectionModel().getSelectedItems();

        if (!selectedItems.isEmpty()) {
            // Display a confirmation dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirm Deletion");
            alert.setContentText("Are you sure you want to delete the selected rule(s)?");

            // Wait for the user's response
            Optional<ButtonType> result = alert.showAndWait();

            // If the user clicks OK, delete the rules
            if (result.isPresent() && result.get() == ButtonType.OK) {
                ruleManager.removeAll(selectedItems);
            }
        } else {
            // Display a warning if no rule is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("No Rule Selected");
            alert.setContentText("Please select a rule to delete.");
            alert.showAndWait();
        }
    }
    /**
     * Activate the selected rule(s) in the TableView.
     * If no rule is selected, display an error message.
     * Refresh the TableView after activating the rule(s).
     *
     * @param actionEvent The action event that triggers the method.
     */
    @FXML
    public void activateRuleAction(ActionEvent actionEvent) {
        ObservableList<Rule> selectedRules = tableView.getSelectionModel().getSelectedItems();

        if (selectedRules.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Rule Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a rule to activate.");
            alert.showAndWait();
            return;  // Exit the method if no rules are selected
        }

        for (Rule selectedRule : selectedRules) {
            selectedRule.setActive(true);
        }
        tableView.refresh();
    }
    /**
     * Deactivates the selected rule(s) in the TableView.
     * If no rule is selected, displays an error message.
     * After deactivating the rule(s), refreshes the TableView.
     *
     * @param actionEvent The action event that triggers the method.
     */
    @FXML
    public void deactivateRuleAction(ActionEvent actionEvent) {
        ObservableList<Rule> selectedRules = tableView.getSelectionModel().getSelectedItems();

        if (selectedRules.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Rule Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a rule to deactivate.");
            alert.showAndWait();
            return;  // Exit the method if no rules are selected
        }

        for (Rule selectedRule : selectedRules) {
            selectedRule.setActive(false);
        }
        tableView.refresh();
    }
    /**
     * Saves the current rules to a binary file and exits the application.
     *
     * @param actionEvent The action event that triggers the method.
     */
    @FXML
    public void quitAction(ActionEvent actionEvent) {
        rbm.saveOnBinaryFile(rules);
        Platform.exit();
        System.exit(0);
    }
    /**
     * Selects all rules in the TableView component.
     *
     * @param event The ActionEvent triggered by selecting all rules.
     */
    @FXML
    private void selectAllRules(ActionEvent event) {
        tableView.getSelectionModel().selectAll();
    }

    /**
     * Deselects all rules in the TableView component.
     *
     * @param event The ActionEvent triggered by deselecting all rules.
     */
    @FXML
    private void deselectAllRules(ActionEvent event) {
        tableView.getSelectionModel().clearSelection();
    }
}
