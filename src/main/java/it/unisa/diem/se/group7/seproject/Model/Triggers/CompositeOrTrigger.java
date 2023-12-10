package it.unisa.diem.se.group7.seproject.Model.Triggers;

import java.util.List;

/**
 * The `CompositeOrTrigger` class is a subclass of `CompositeTrigger` that represents a composite trigger with an "OR" logic.
 * It evaluates to true if any of its underlying triggers evaluate to true.
 * <p>
 * It has two constructors:
 * 1. `CompositeOrTrigger()`, which initializes an empty composite trigger.
 * 2. `CompositeOrTrigger(Trigger... triggers)`, which initializes the composite trigger with the given triggers.
 * <p>
 * The `evaluate()` method checks each trigger in the composite trigger and returns true if any one of them evaluates to true.
 * It returns false if all triggers evaluate to false.
 * <p>
 * The `toString()` method returns a string representation of the composite trigger, showing the individual triggers separated by an "OR" symbol.
 */
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
