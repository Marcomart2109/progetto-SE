package it.unisa.diem.se.group7.seproject.Views.TriggerViews;

import it.unisa.diem.se.group7.seproject.Model.Triggers.DayOfTheWeekTrigger;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * The DayOfTheWeekTriggerView class is an implementation of the TriggerView interface that
 * allows the user to select a specific day of the week as a trigger.
 */
public class DayOfTheWeekTriggerView implements TriggerView {
    private final ComboBox<DayOfWeek> dayComboBox;

    public DayOfTheWeekTriggerView() {
        dayComboBox = new ComboBox<>();
        ObservableList<DayOfWeek> daysOfWeek = FXCollections.observableArrayList(Arrays.asList(DayOfWeek.values()));
        dayComboBox.setItems(daysOfWeek);

        // Select the current day by default
        dayComboBox.getSelectionModel().select(LocalDate.now().getDayOfWeek());
    }


    @Override
    public Node getView() {
        VBox container = new VBox();
        container.setSpacing(DEFAULT_SPACING);
        Label label = new Label("Select Day of the Week:");

        container.getChildren().addAll(label, dayComboBox);

        return container;
    }

    private DayOfWeek getSelectedDay() {
        return dayComboBox.getValue();
    }
    @Override
    public Trigger getTrigger() {
        return new DayOfTheWeekTrigger(getSelectedDay());
    }

    @Override
    public boolean isValid() {
        DayOfWeek selectedDay = getSelectedDay();
        return selectedDay != null;
    }
}
