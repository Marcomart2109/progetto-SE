package it.unisa.diem.se.group7.seproject.Model.Triggers;

import java.io.Serializable;

public class NotTriggerDecorator implements Trigger, Serializable {

    private Trigger trigger;

    public NotTriggerDecorator(Trigger trigger) {
        this.trigger = trigger;
    }

    @Override
    public boolean evaluate() {
        return !trigger.evaluate();
    }

}