package it.unisa.diem.se.group7.seproject.Views.ActionViews;

import javafx.scene.Node;
import it.unisa.diem.se.group7.seproject.Model.Actions.Action;

public interface ActionView {
    public static final double PREF_TEXTFIELD_SIZE = 200.0;
    public static final double DEFAULT_SPACING = 10;
    public static final double INCREASED_SPACING = 20;

    Node getView();

    Action getAction();
    boolean isValid();
}
