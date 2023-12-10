package it.unisa.diem.se.group7.seproject.Views.TriggerViews;

import it.unisa.diem.se.group7.seproject.Model.Triggers.NotTriggerDecorator;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;
import it.unisa.diem.se.group7.seproject.Model.Triggers.TriggerType;
import it.unisa.diem.se.group7.seproject.Views.Factories.TriggerViewFactory;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * ElementaryTriggerView is a class that implements the TriggerView interface.
 * It represents a view for elementary triggers, allowing users to select a trigger type and configure it.
 * The view contains a ComboBox to select a trigger type, a CheckBox to negate the trigger, and a container to display the trigger-specific configuration.
 *
 */
public class ElementaryTriggerView implements TriggerView {
    private final HBox triggersBox;
    private final ComboBox<TriggerType> triggerMenu;
    private TriggerView currentTriggerView;
    private final CheckBox negateTriggerCheckbox;

    public ElementaryTriggerView() {
        triggersBox = new HBox(DEFAULT_SPACING);
        triggerMenu = new ComboBox<>();
        negateTriggerCheckbox = new CheckBox();
        triggerMenu.getItems().setAll(TriggerType.values());
    }

    @Override
    public Node getView() {
        VBox container = new VBox();
        container.setSpacing(DEFAULT_SPACING);

        HBox triggerContainer = new HBox(DEFAULT_SPACING);
        triggerContainer.setAlignment(Pos.CENTER_LEFT);

        Label triggerLabel = new Label("Select a trigger");


        triggerMenu.setOnAction(actionEvent -> handleTriggerChoice());
        Label negateTriggerLabel = new Label("NOT");
        triggerContainer.getChildren().addAll(negateTriggerLabel, negateTriggerCheckbox, triggerMenu, triggersBox);

        container.getChildren().addAll(triggerLabel, triggerContainer);

        return container;
    }

    private void handleTriggerChoice() {
        triggersBox.getChildren().clear();
        TriggerType selectedTriggerType = triggerMenu.getValue();
        currentTriggerView = TriggerViewFactory.createView(selectedTriggerType);
        Separator separator = new Separator(Orientation.VERTICAL);
        triggersBox.getChildren().addAll(separator, currentTriggerView.getView());
    }

    @Override
    public Trigger getTrigger() {
        if (negateTriggerCheckbox.isSelected()) {
            return new NotTriggerDecorator(currentTriggerView.getTrigger());
        }
        return currentTriggerView.getTrigger();
    }

    @Override
    public boolean isValid() {
        return currentTriggerView != null && currentTriggerView.isValid();
    }
}
