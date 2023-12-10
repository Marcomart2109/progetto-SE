package it.unisa.diem.se.group7.seproject.Model.Rules;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;
import javafx.beans.property.SimpleBooleanProperty;

import java.io.*;

/**
 * The SimpleRule class represents a rule that can be evaluated and executed based on its trigger and action.
 * It extends the Rule abstract class and implements the Serializable interface.
 * <p>
 * A SimpleRule has a name, a trigger, an action, and an active state.
 * The active state represents the current activation state of the rule and can be observed and modified using the active property.
 * The active property is transient, meaning it will not be serialized or deserialized.
 */
public class SimpleRule extends Rule implements Serializable {

    private Boolean fired;

    public SimpleRule(String name, Trigger trigger, Action action) {
        super(name, trigger, action);
        this.fired = false;
    }

    @Override
    public boolean evaluate() {
        if(!fired && getTrigger().evaluate() && isActive()) {
            System.out.println("Rule \"" + getName() + "\"is triggered");
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        getAction().execute();
        fired = true;
        setActive(false);
    }

    public boolean isFired() {
        return fired;
    }

    public void setFired(boolean fired) {
        this.fired = fired;
    }

    @Override
    public void setTrigger(Trigger trigger) {
        fired = false;
        super.setTrigger(trigger);

    }

    @Override
    public void setAction(Action action) {
        fired = false;
        super.setAction(action);
    }

    // Custom serialization method for the BooleanProperty
    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeBoolean(activeProperty().get()); // Write the active status
    }

    // Custom deserialization method
    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        boolean isActive = in.readBoolean(); // Read the active status
        setActiveProperty(new SimpleBooleanProperty(isActive));
    }

}



