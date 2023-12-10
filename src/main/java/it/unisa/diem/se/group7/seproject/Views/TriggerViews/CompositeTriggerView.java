package it.unisa.diem.se.group7.seproject.Views.TriggerViews;

import it.unisa.diem.se.group7.seproject.Model.Triggers.CompositeAndTrigger;
import it.unisa.diem.se.group7.seproject.Model.Triggers.CompositeOrTrigger;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


/**
 * The CompositeTriggerView class implements the TriggerView interface.
 * It represents a composite trigger view that combines multiple triggers using a selected comparator.
 * The composite trigger view contains two ElementaryTriggerViews and a ComboBox to select the comparator.
 * <p>
 * The getView() method returns a Node that represents the composite trigger view.
 * The getTrigger() method returns a Trigger that combines the triggers based on the selected comparator.
 * The isValid() method checks if the composite trigger view and its components are valid.
 */
public class CompositeTriggerView implements TriggerView {

    private final TriggerView firstTriggerView;
    private final TriggerView secondTriggerView;
    private final ComboBox<String> triggerComparator;

    public CompositeTriggerView() {
        firstTriggerView = new ElementaryTriggerView();
        secondTriggerView = new ElementaryTriggerView();
        triggerComparator = new ComboBox<>();
        triggerComparator.getItems().addAll("AND", "OR");
        triggerComparator.setValue("AND");
    }

    @Override
    public Node getView() {
        VBox container = new VBox();
        container.setSpacing(DEFAULT_SPACING);
        container.getChildren().addAll(firstTriggerView.getView(), new Label("Select a comparator"), triggerComparator, secondTriggerView.getView());


        return container;
    }

    @Override
    public Trigger getTrigger() {
        // Implement logic to combine the triggers based on the selected comparator
        Trigger firstTrigger = firstTriggerView.getTrigger();
        Trigger secondTrigger = secondTriggerView.getTrigger();
        String comparator = triggerComparator.getValue();
        if (comparator.equals("AND")) {
            return new CompositeAndTrigger(firstTrigger, secondTrigger);
        } else if (comparator.equals("OR")) {
            return new CompositeOrTrigger(firstTrigger, secondTrigger);
        } else {
            throw new IllegalStateException("Invalid comparator selected");
        }
    }

    @Override
    public boolean isValid() {
        boolean firstTriggerValid = firstTriggerView != null && firstTriggerView.isValid();
        boolean secondTriggerValid = secondTriggerView != null && secondTriggerView.isValid();
        boolean comparatorSelected = triggerComparator.getValue() != null;

        return firstTriggerValid && secondTriggerValid && comparatorSelected;
    }
}
