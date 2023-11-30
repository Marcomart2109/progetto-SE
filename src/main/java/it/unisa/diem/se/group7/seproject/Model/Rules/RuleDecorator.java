package it.unisa.diem.se.group7.seproject.Model.Rules;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;

import java.io.Serializable;

public abstract class RuleDecorator implements Rule, Serializable {
    private Rule rule;
    private String name;
    private Action action;
    private Trigger trigger;
    private boolean fired;

    public abstract boolean evaluate();

    public abstract void execute();

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Action getAction() {
        return action;
    }

    @Override
    public Trigger getTrigger() {
        return trigger;
    }

    @Override
    public void setTrigger(Trigger trigger) {
        setFired(false);
    }

    @Override
    public void setAction(Action action) {

    }
    @Override
    public boolean isFired() {
        return fired;
    }

    @Override
    public void setFired(boolean fired) {
        this.fired = fired;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
