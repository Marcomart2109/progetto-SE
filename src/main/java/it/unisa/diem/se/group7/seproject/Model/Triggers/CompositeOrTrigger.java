package it.unisa.diem.se.group7.seproject.Model.Triggers;

import java.util.List;

public class CompositeOrTrigger extends CompositeTrigger {
    @Override
    public boolean evaluate() {
        List<Trigger> triggers = this.getTriggers();


        for(Trigger t : triggers){
            if(t.evaluate()) {
                return true;
            }
        }

        return false;
    }
}
