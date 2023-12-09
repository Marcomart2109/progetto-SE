package it.unisa.diem.se.group7.seproject.Model.Actions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompositeAction implements Action, Serializable {
    private List<Action> actions;

    public CompositeAction() {
        actions = new ArrayList<>();
    }

    public CompositeAction(Action... actions) {
        this.actions = new ArrayList<>();
        this.actions.addAll(Arrays.asList(actions));
    }

    public void add(Action action) {
        actions.add(action);
    }
    public List<Action> getActions() {
        return actions;
    }
    public void remove(Action action) {
        actions.remove(action);
    }

    @Override
    public void execute() {
        for (Action action : actions) {
            action.execute();
        }
    }

    @Override
    public String toString() {
        return actions.toString();
    }
}
