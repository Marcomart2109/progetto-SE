package it.unisa.diem.se.group7.seproject.Model.Rules;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;
import javafx.beans.property.SimpleBooleanProperty;

import java.io.*;

public class SimpleRule extends Rule implements Serializable{

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



