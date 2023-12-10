package it.unisa.diem.se.group7.seproject.Model.Rules;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.io.*;

/**
 * The abstract class Rule represents a rule that can be evaluated and executed based on its trigger and action.
 * A Rule has a name, a trigger, an action, and an active state.
 * The active state represents the current activation state of a Rule and can be observed and modified using the active property.
 * The active property is transient, meaning it will not be serialized or deserialized.
 */
public abstract class Rule implements Serializable {
     private String name;
     private Trigger trigger;
     private Action action;
     private transient BooleanProperty active;

    public Rule(String name, Trigger trigger, Action action) {
        this.name = name;
        this.trigger = trigger;
        this.action = action;
        this.active = new SimpleBooleanProperty(true);
    }

    public Rule(){
    }

    public boolean evaluate() {
        return trigger.evaluate();
    }

    public abstract void execute();

    public String getName() {
        return name;
    }

    public Action getAction() {
        return action;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public boolean isActive() {
        return active.get();
    }
    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActive(boolean active) {
        this.active.set(active);
    }
    public BooleanProperty activeProperty() {
        return active;
    }

    public void setActiveProperty(BooleanProperty active) {
        this.active = active;
    }

}
