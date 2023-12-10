package it.unisa.diem.se.group7.seproject.Views.TriggerViews;

import it.unisa.diem.se.group7.seproject.Model.Triggers.DayOfTheYearTrigger;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

/**
 * DayOfTheYearTriggerView is a class that implements the TriggerView interface.
 * It represents a view for selecting a specific day of the year as a trigger.
 *
 * <p> The selected day of the year is used to create a DayOfTheYearTrigger instance,
 * which is then used as the trigger for evaluating events or actions.
 *
 * <p> The view provides a DatePicker component for selecting the day of the year.
 * It disables days before the current date to prevent invalid selections.
 */
public class DayOfTheYearTriggerView implements TriggerView {
    private final DatePicker datePicker;

    public DayOfTheYearTriggerView() {
        datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());

        // Set the custom DayCellFactory to disable days before the current date
        datePicker.setDayCellFactory(this::createDayCell);
    }

    @Override
    public Node getView() {
        VBox container = new VBox();
        container.setSpacing(DEFAULT_SPACING);

        Label label = new Label("Select Day of the Year:");

        HBox dateBox = new HBox(DEFAULT_SPACING);
        dateBox.setAlignment(Pos.CENTER_LEFT);
        dateBox.getChildren().addAll(datePicker);

        container.getChildren().addAll(label, dateBox);

        return container;
    }

    private LocalDate getSelectedDate() {
        return datePicker.getValue();
    }

    @Override
    public Trigger getTrigger() {
        return new DayOfTheYearTrigger(getSelectedDate());
    }

    @Override
    public boolean isValid() {
        LocalDate selectedDate = datePicker.getValue();

        // Check if the selected date is not null
        return selectedDate != null;
    }

    // Custom DayCellFactory to disable days before the current date
    private DateCell createDayCell(DatePicker datePicker) {
        return new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                LocalDate today = LocalDate.now();

                // Disable days before today
                setDisable(item.isBefore(today));
            }
        };
    }
}
