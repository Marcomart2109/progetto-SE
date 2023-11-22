package it.unisa.diem.se.group7.seproject.Controller;

import it.unisa.diem.se.group7.seproject.Model.Rule;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TableColumn<Rule, Integer> indexClm;

    @FXML
    private TableColumn<Rule, String> rulesClm;

    @FXML
    private TableView<Rule> tableView;

    private final ObservableList<Rule> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableList.add(
                new Rule("Rule1")
        );
        rulesClm.setCellValueFactory(new PropertyValueFactory<Rule, String>("name"));
        indexClm.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(observableList.indexOf(cellData.getValue()) + 1));

/*        indexClm.setCellValueFactory(new PropertyValueFactory<Rule,Trigger>("trigger"));
        rulesClm.setCellValueFactory(new PropertyValueFactory<Rule,Action>("action"));*/

        tableView.setItems(observableList);

    }

    @FXML
    public void addNewRule() {
        // Code to add a new rule to the observableList
    }

}
