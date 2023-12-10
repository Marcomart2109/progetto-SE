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
    public void add(Action action){
        if(actions.contains(action)) {
            throw new RuntimeException("The specified action is already in the list!");
        }else{
            actions.add(action);
        }

    }
    public void remove(Action action){

        if(!actions.remove(action)){
            throw new RuntimeException("The specified action is not contained in the list!");
        }
    }
    public List<Action> getActions(){

        return actions;
    }
    @Override
    public void execute() {
        for (Action action : actions) {
            action.execute();
        }
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder();
        int actionNumber = 1;

        for(Action action: actions) {
            sb.append(actionNumber);
            sb.append(" - ");
            sb.append(action.toString());
            sb.append("\n");
            actionNumber++;
        }
        return sb.toString();
    }
}
