package it.unisa.diem.se.group7.seproject.Views.TriggerViews;

import it.unisa.diem.se.group7.seproject.Model.Triggers.TimeTrigger;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class TimeTriggerView implements TriggerView {
    private Spinner<Integer> hourSpinner;
    private Spinner<Integer> minuteSpinner;

    public TimeTriggerView() {
        hourSpinner = new Spinner<>(0, 23, 0);
        minuteSpinner = new Spinner<>(0, 59, 0);
    }

    @Override
    public Node getView() {
        VBox container = new VBox();

        Label label = new Label("Select Time:");
        label.setStyle("-fx-font-weight: bold");

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
