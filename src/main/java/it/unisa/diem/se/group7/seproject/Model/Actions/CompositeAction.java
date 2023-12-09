package it.unisa.diem.se.group7.seproject.Model.Actions;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
public class CompositeAction implements Action, Serializable {

    private List<Action>  actions;
    private static final int ACTION_LIMIT = 2;

    public CompositeAction(){

        actions = new ArrayList<>();
    }
    public void add(Action action){
        if(actions.contains(action)) {
            throw new RuntimeException("The specified action is already in the list!");
        }
        if(actions.size() < ACTION_LIMIT){
            actions.add(action);
            actions.add(action);
        }else{
            throw new RuntimeException("The list already contains two triggers");
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

}
