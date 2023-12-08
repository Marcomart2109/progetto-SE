package it.unisa.diem.se.group7.seproject.Views.TriggerViews;

import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;
import javafx.scene.Node;

public interface TriggerView {
    public static final double DEFAULT_SPACING = 10;
    public static final double INCREASED_SPACING = 20;

    Node getView();
    Trigger getTrigger();
    boolean isValid();
}
