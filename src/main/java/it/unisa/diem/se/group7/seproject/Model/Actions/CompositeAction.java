package it.unisa.diem.se.group7.seproject.Model.Actions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The CompositeAction class represents a composite action that can be executed.
 * It implements the Action and Serializable interfaces.
 * <p>
 * A composite action is a collection of individual actions that are executed in sequence.
 * When the composite action is executed, each individual action in the collection is executed in the order they were added.
 * <p>
 * Example Usage:
 * <p>
 * // Create an instance of CompositeAction
 * CompositeAction compositeAction = new CompositeAction();
 * <p>
 * // Create individual actions
 * Action action1 = new TrueAction();
 * Action action2 = new FalseAction();
 * <p>
 * // Add individual actions to composite action
 * compositeAction.add(action1);
 * compositeAction.add(action2);
 * <p>
 * // Execute the composite action
 * compositeAction.execute();
 *
 */
public class CompositeAction implements Action, Serializable {
    private final List<Action> actions;

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
