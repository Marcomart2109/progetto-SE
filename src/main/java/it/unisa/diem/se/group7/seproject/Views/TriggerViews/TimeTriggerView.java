package it.unisa.diem.se.group7.seproject.Views.TriggerViews;

import it.unisa.diem.se.group7.seproject.Model.Triggers.TimeTrigger;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.time.LocalTime;

/**
 * The TimeTriggerView class is an implementation of the TriggerView interface that allows the user to select a specific time as a trigger.
 * The user can select the hour and minute using spinner components.
 */
public class TimeTriggerView implements TriggerView {
    private final Spinner<Integer> hourSpinner;
    private final Spinner<Integer> minuteSpinner;

    public TimeTriggerView() {
        hourSpinner = new Spinner<>();
        minuteSpinner = new Spinner<>();

        //Setup spinner component for time and minutes
        Integer currenthours = LocalTime.now().getHour();
        Integer currentminutes = LocalTime.now().getMinute();

        SpinnerValueFactory<Integer> hourValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        SpinnerValueFactory<Integer> minuteValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        hourValueFactory.setValue(currenthours);
        minuteValueFactory.setValue(currentminutes);

        hourSpinner.setValueFactory(hourValueFactory);
        minuteSpinner.setValueFactory(minuteValueFactory);

        hourSpinner.setEditable(true);
        minuteSpinner.setEditable(true);

        hourSpinner.setPrefWidth(60);
        minuteSpinner.setPrefWidth(60);
    }

    @Override
    public Node getView() {
        VBox container = new VBox();
        container.setSpacing(DEFAULT_SPACING);

        Label label = new Label("Select Time:");
        HBox spinnerBox = new HBox(10);
        spinnerBox.setAlignment(Pos.CENTER);
        spinnerBox.getChildren().addAll(new Label("Hour:"), hourSpinner, new Label("Minute:"), minuteSpinner);

        container.getChildren().addAll(label, spinnerBox);

        return container;
    }

    private int getSelectedHour() {
        return hourSpinner.getValue();
    }

    private int getSelectedMinute() {
        return minuteSpinner.getValue();
    }

    @Override
    public Trigger getTrigger() {
        return new TimeTrigger(getSelectedHour(), getSelectedMinute());
    }

    @Override
    public boolean isValid() {
        Integer selectedHour = hourSpinner.getValue();
        Integer selectedMinute = minuteSpinner.getValue();

        // Check if both hour and minute are not null
        return selectedHour != null && selectedMinute != null;
    }

}
