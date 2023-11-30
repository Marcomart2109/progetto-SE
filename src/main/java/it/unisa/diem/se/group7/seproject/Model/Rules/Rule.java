package it.unisa.diem.se.group7.seproject.Model.Rules;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;

import java.io.Serializable;

public interface Rule {


    public abstract boolean evaluate();

    public abstract void execute();

    public abstract String getName();

    public abstract Action getAction();

    public abstract Trigger getTrigger();

    public abstract boolean isFired();

    public abstract void setFired(boolean fired);

    public abstract void setTrigger(Trigger trigger);

    public abstract void setAction(Action action);

    public abstract void setName(String name);

}
