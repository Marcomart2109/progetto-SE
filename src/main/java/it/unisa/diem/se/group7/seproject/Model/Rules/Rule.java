package it.unisa.diem.se.group7.seproject.Model.Rules;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.io.*;

public abstract class Rule implements Serializable {
     private String name;
     private Trigger trigger;
     private Action action;
     private transient BooleanProperty active; // activation status of the current rule

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
