package it.unisa.diem.se.group7.seproject.Model.Rules;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;

import java.io.Serializable;

public class SimpleRule extends Rule {

    private Boolean fired;

    public SimpleRule(String name, Trigger trigger, Action action) {
        super(name, trigger, action);
        this.fired = false;
    }

    @Override
    public boolean evaluate() {
        if(!fired && trigger.evaluate()) {
            System.out.println("Rule \"" + getName() + "\"is triggered");
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        action.execute();
        fired = true;
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



