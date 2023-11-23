package it.unisa.diem.se.group7.seproject.Controller;

import it.unisa.diem.se.group7.seproject.Model.Rule;
import it.unisa.diem.se.group7.seproject.Model.RuleManager;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TableColumn<Rule, Integer> indexClm;

    @FXML
    private TableColumn<Rule, String> rulesClm;

    @FXML
    private TableView<Rule> tableView;

    private RuleManager ruleManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ruleManager = RuleManager.getInstance();
        ObservableList<Rule> rules = ruleManager.getRules();

        rules.add(
                new Rule("Rule1")
        );
        rulesClm.setCellValueFactory(new PropertyValueFactory<Rule, String>("name"));
        indexClm.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(rules.indexOf(cellData.getValue()) + 1));

/*        indexClm.setCellValueFactory(new PropertyValueFactory<Rule,Trigger>("trigger"));
        rulesClm.setCellValueFactory(new PropertyValueFactory<Rule,Action>("action"));*/

        tableView.setItems(rules);

    }

    //
    public void addNewRule() {
        try {
            // Load the FXML file for the new view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/unisa/diem/se/group7/seproject/rule-view.fxml"));
            Parent root = loader.load();

            // Create a new stage for the new view
            Stage newStage = new Stage();
            newStage.initStyle(StageStyle.UTILITY); // You can change the style as needed
            newStage.initModality(Modality.APPLICATION_MODAL); // Block events to other windows
            newStage.setTitle("Desktop RuleMaster - Create a new rule");

            // Set the new scene on the stage
            Scene scene = new Scene(root);
            newStage.setScene(scene);

            // Show the new stage
            newStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
