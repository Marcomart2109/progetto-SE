package it.unisa.diem.se.group7.seproject.Model.Triggers;

import java.util.List;

public class CompositeAndTrigger extends CompositeTrigger{

    public CompositeAndTrigger(){
        super();
    }

    @Override
    public boolean evaluate() {
        List<Trigger> triggers = this.getTriggers();

        boolean result = true;

        for(Trigger t : triggers){
            result = result && t.evaluate();
        }

        return result;
    }

    @Override
    public TriggerType getTYPE() {
        return null;
    }
}
