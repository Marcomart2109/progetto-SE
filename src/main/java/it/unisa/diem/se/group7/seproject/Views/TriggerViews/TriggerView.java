package it.unisa.diem.se.group7.seproject.Views.TriggerViews;

import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;
import javafx.scene.Node;

public interface TriggerView {
    Node getView();
    Trigger getTrigger();
    boolean isValid();
}
