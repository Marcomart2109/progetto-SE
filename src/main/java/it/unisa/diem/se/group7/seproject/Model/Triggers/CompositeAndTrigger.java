package it.unisa.diem.se.group7.seproject.Model.Triggers;

import java.util.List;

public class CompositeAndTrigger extends CompositeTrigger{

    public CompositeAndTrigger(){
        super();
    }

    @Override
    public boolean evaluate() {
        List<Trigger> triggers = this.getTriggers();

        for(Trigger t : triggers){
            if (!t.evaluate()) {
                return false;
            }
        }

        return true;
    }

}
