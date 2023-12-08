package it.unisa.diem.se.group7.seproject.Views.ActionViews;

import javafx.scene.Node;
import it.unisa.diem.se.group7.seproject.Model.Actions.Action;

public interface ActionView {
    Node getView();

    Action getAction();
    boolean isValid();
}
