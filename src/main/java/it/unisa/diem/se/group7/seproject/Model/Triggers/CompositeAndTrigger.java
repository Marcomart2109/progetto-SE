package it.unisa.diem.se.group7.seproject.Model.Triggers;

import java.util.List;

public class CompositeAndTrigger extends CompositeTrigger{

    public CompositeAndTrigger(){
        super();
    }
    public CompositeAndTrigger(Trigger... triggers) {
        super(triggers);
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
    @Override
    public String toString() {
        return getTriggers().get(0) + "\nAND\n" + getTriggers().get(1);
    }

}
