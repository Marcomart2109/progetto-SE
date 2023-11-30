package it.unisa.diem.se.group7.seproject.Model.Rules;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;

import java.io.Serializable;

public class SimpleRule implements Rule, Serializable {

    private String name;
    private Action action;
    private Trigger trigger;
    private Boolean fired;

    public SimpleRule(String name, Trigger trigger, Action action) {
        this.name = name;
        this.action = action;
        this.trigger = trigger;
        this.fired = false;
    }

    @Override
    public boolean evaluate() {
        return trigger.evaluate();
    }

    @Override
    public void execute() {
        if (!isFired()) {
            action.execute();
            System.out.println("Rule \"" + name + "\"is triggered");
            fired = true;
        }
    }

    @Override
    public Trigger getTrigger() {
        return trigger;
    }


    @Override
    public Action getAction() {
        return action;
    }


    @Override
    public String getName() {
        return name;
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
        this.trigger = trigger;

    }

    @Override
    public void setAction(Action action) {
        fired = false;
        this.action = action;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}



