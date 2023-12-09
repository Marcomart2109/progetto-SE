package it.unisa.diem.se.group7.seproject.Model.Triggers;

import java.util.List;

public class CompositeOrTrigger extends CompositeTrigger {
    public CompositeOrTrigger() {
        super();
    }
    public CompositeOrTrigger(Trigger... triggers) {
        super(triggers);
    }
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

    @Override
    public String toString() {
        return getTriggers().get(0) + "\nOR\n" + getTriggers().get(1);
    }
}
