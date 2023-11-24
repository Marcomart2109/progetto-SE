package it.unisa.diem.se.group7.seproject.Model.Rules;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;

public class Rule {

    String name;
    Action action;
    Trigger trigger;
    boolean fired;

    public Rule(String name, String timeTrigger, String showDialogBox) {
        this.name = name;
        this.fired = false;
        this.action = null;
        this.trigger = null;
    }

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
        this.action = action;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    public boolean isFired() {
        return fired;
    }

    private void setFired(boolean fired) {
        this.fired = fired;
    }
}
