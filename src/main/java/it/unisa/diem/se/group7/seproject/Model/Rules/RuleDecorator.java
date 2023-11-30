package it.unisa.diem.se.group7.seproject.Model.Rules;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;

import java.io.Serializable;

public abstract class RuleDecorator implements Rule, Serializable {
    private Rule rule;
    private String name;
    private Action action;
    private Trigger trigger;

    public RuleDecorator(Rule rule, String name, Trigger trigger, Action action) {
        this.rule = rule;
        this.name = name;
        this.action = action;
        this.trigger = trigger;
    }

    @Override
    public abstract boolean evaluate();
    @Override
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
    public void setAction(Action action) {
        this.action = action;
    }
    @Override
    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
