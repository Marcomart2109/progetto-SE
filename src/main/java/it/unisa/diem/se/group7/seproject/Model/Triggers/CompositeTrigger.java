package it.unisa.diem.se.group7.seproject.Model.Triggers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class CompositeTrigger implements Trigger, Serializable {
    private List<Trigger> triggers;
    private static final int TRIGGER_LIMIT = 2;

    public CompositeTrigger(){
        triggers = new ArrayList<Trigger>();
    }

    public void add(Trigger t){
        if(triggers.contains(t)){
            throw new RuntimeException("The specified trigger is already contained in the list!");
        }
        if(triggers.size() < TRIGGER_LIMIT){
            triggers.add(t);
        }else{
            throw new RuntimeException("The list already contains two triggers");
        }
    }

    public void remove(Trigger t){
        if(!triggers.remove(t)){
            throw new RuntimeException("The specified trigger is not contained in the list!");
        }
    }

    public List<Trigger> getTriggers(){
        return triggers;
    }

}
