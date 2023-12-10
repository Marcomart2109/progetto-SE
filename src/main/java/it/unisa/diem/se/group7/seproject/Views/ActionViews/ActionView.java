package it.unisa.diem.se.group7.seproject.Views.ActionViews;

import javafx.scene.Node;
import it.unisa.diem.se.group7.seproject.Model.Actions.Action;

/**
 * The ActionView interface represents a view for an action.
 * Implementations of this interface should provide methods to retrieve the view,
 * the corresponding action, and a flag to determine if the view is valid.
 */
public interface ActionView {
    double PREF_TEXTFIELD_SIZE = 200.0;

    double DEFAULT_SPACING = 10;
    double INCREASED_SPACING = 20;

    Node getView();
    Action getAction();
    boolean isValid();
}
