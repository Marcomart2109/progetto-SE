package it.unisa.diem.se.group7.seproject.Views.TriggerViews;

import it.unisa.diem.se.group7.seproject.Model.Triggers.DayOfTheMonthTrigger;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class DayOfMonthTriggerView implements TriggerView {

    private Spinner<Integer> daySpinner;

    public DayOfMonthTriggerView() {
        daySpinner = new Spinner<>();
        SpinnerValueFactory<Integer> dayOfTheMonthFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 31);
        // Current day as default value
        dayOfTheMonthFactory.setValue(LocalDate.now().getDayOfMonth());
        daySpinner.setValueFactory(dayOfTheMonthFactory);
        daySpinner.setEditable(true);
        daySpinner.setPrefWidth(60);
    }

    @Override
    public Node getView() {
        VBox container = new VBox();
        container.setSpacing(DEFAULT_SPACING);

        Label label = new Label("Select Day of the Month:");

        HBox spinnerBox = new HBox(DEFAULT_SPACING);
        spinnerBox.setAlignment(Pos.CENTER_LEFT);
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
