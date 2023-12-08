package it.unisa.diem.se.group7.seproject.Views.TriggerViews;

import it.unisa.diem.se.group7.seproject.Model.Triggers.DayOfTheYearTrigger;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;
import javafx.scene.Node;

import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class DayOfTheYearTriggerView implements TriggerView {
    private DatePicker datePicker;

    public DayOfTheYearTriggerView() {
        datePicker = new DatePicker();
    }

    @Override
    public Node getView() {
        VBox container = new VBox();

        container.getChildren().addAll(datePicker);

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
}

