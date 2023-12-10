package it.unisa.diem.se.group7.seproject.Views.TriggerViews;

import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;
import javafx.scene.Node;

/**
 * The TriggerView interface represents a view for a trigger, which is used to configure and create triggers.
 * A trigger view provides a way for the user to select and configure a specific type of trigger.
 */
public interface TriggerView {
    double DEFAULT_SPACING = 10;
    double INCREASED_SPACING = 20;
    Node getView();
    Trigger getTrigger();
    boolean isValid();
}
