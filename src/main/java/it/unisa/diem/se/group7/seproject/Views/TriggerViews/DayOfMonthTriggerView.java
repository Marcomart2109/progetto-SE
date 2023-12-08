package it.unisa.diem.se.group7.seproject.Views.TriggerViews;

import it.unisa.diem.se.group7.seproject.Model.Triggers.DayOfTheMonthTrigger;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DayOfMonthTriggerView implements TriggerView {
    private static final int MIN_DAY = 1;
    private static final int MAX_DAY = 31;

    private Spinner<Integer> daySpinner;

    public DayOfMonthTriggerView() {
        daySpinner = new Spinner<>(MIN_DAY, MAX_DAY, MIN_DAY);
    }

    @Override
    public Node getView() {
        VBox container = new VBox();

        Label label = new Label("Select Day of the Month:");
        label.setStyle("-fx-font-weight: bold");

        HBox spinnerBox = new HBox(10);
        spinnerBox.getChildren().addAll(new Label("Day:"), daySpinner);

        container.getChildren().addAll(label, spinnerBox);

        return container;
    }

    private int getSelectedDay() {
        return daySpinner.getValue();
    }

    @Override
    public Trigger getTrigger() {
        return new DayOfTheMonthTrigger(getSelectedDay());
    }

    @Override
    public boolean isValid() {
        Integer selectedDay = daySpinner.getValue();

        // Check if the selected day is not null
        return selectedDay != null;
    }
}
