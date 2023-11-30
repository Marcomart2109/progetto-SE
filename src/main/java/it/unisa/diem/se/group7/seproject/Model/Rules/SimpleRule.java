package it.unisa.diem.se.group7.seproject.Model.Rules;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;

public class SimpleRule implements Rule{

    private String name;
    private Action action;
    private Trigger trigger;
    private Boolean active;
    private Boolean fired;

    public void Rule(String name, Trigger trigger, Action action) {
        this.name = name;
        this.action = action;
        this.trigger = trigger;
        this.fired = false;
    }
    @Override
    public boolean evaluate() {
        return false;
    }

    @Override
    public void execute() {

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
        return false;
    }
}


