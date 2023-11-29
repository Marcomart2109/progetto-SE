package it.unisa.diem.se.group7.seproject.Model.Rules;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;

import java.io.Serializable;

public class Rule implements Serializable {

    private String name;
    private Action action;
    private Trigger trigger;
    private boolean fired;

    public Rule(String name, Trigger trigger, Action action) {
        this.name = name;
        this.action = action;
        this.trigger = trigger;
        this.fired = false;
    }

    public boolean evaluate() {
        return trigger.evaluate();
    }

    public void execute() {
        if(!isFired()) {
            action.execute();
            System.out.println("Rule \"" + this.name + "\" is triggered");
            setFired(true);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        //Whenever I change the action the rule became firable again
        fired = false;
        this.action = action;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger trigger) {
        //Whenever I change the trigger the rule became firable again
        fired = false;
        this.trigger = trigger;
    }

    public boolean isFired() {
        return fired;
    }

    private void setFired(boolean fired) {
        this.fired = fired;
    }
}
