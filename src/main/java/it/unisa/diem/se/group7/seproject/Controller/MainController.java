package it.unisa.diem.se.group7.seproject.Controller;

import it.unisa.diem.se.group7.seproject.Application;
import it.unisa.diem.se.group7.seproject.Model.Rules.Rule;
import it.unisa.diem.se.group7.seproject.Model.Rules.RuleBackup;
import it.unisa.diem.se.group7.seproject.Model.Rules.RuleManager;
import javafx.beans.binding.Bindings;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TableColumn<Rule, Integer> indexClm;

    @FXML
    private TableColumn<Rule, String> rulesClm;

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

    private ObservableList<Rule> rules;

    private RuleManager ruleManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ruleManager = RuleManager.getInstance();
        rules = ruleManager.getRules();

        // load data from the binary file with the specified path to rules ObservableList
        String backupPath = "src/main/resources/saved.bin";
        RuleBackup.loadFromBinaryFile(rules, backupPath);

        initTableView();
        initDetailBox();
    }

    /**
     * Initializes the TableView by setting up the cell value factories, cell factories, and event handlers.
     * The TableView displays a list of Rule objects.
     * The name property of each Rule is displayed in the rulesClm column.
     * The index of each Rule object in the rules list plus one is displayed in the indexClm column.
     * The rulesClm column uses a TextFieldTableCell cell factory for editing the name property.
     * When the name is edited in the rulesClm column, the corresponding Rule object's name property is updated.
     *
     * Multiple selection is enabled on the TableView.
     * The TableView is populated with the rules list.
     */
    private void initTableView() {

        rulesClm.setCellValueFactory(new PropertyValueFactory<>("name"));
        indexClm.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(rules.indexOf(cellData.getValue()) + 1));
        rulesClm.setCellFactory(TextFieldTableCell.forTableColumn());
        rulesClm.setOnEditCommit(event -> {
            Rule rule = event.getRowValue();
            rule.setName(event.getNewValue());
        });
/*        indexClm.setCellValueFactory(new PropertyValueFactory<Rule,Trigger>("trigger"));
        rulesClm.setCellValueFactory(new PropertyValueFactory<Rule,Action>("action"));*/

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
            String cssPath = Application.class.getResource("style/style.css").toExternalForm();
            scene.getStylesheets().add(cssPath);

            // Show the new stage
            newStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void editRuleAction(ActionEvent event) {
        ObservableList<Rule> selectedItems = tableView.getSelectionModel().getSelectedItems();

        if (selectedItems.size() == 1) {
            Rule selectedItem = selectedItems.get(0);
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
                String cssPath = Application.class.getResource("style/style.css").toExternalForm();
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

}
