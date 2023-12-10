package it.unisa.diem.se.group7.seproject.Model.Triggers;

import java.util.List;

/**
 * The `CompositeAndTrigger` class is a concrete implementation of the `CompositeTrigger` abstract class.
 * It represents a composite trigger that combines multiple triggers using the logical AND operator.
 * The trigger is considered true only if all the triggers it contains evaluate to true.
 */
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
