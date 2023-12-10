package it.unisa.diem.se.group7.seproject.Model.Triggers;

import java.io.Serializable;

/**
 * The NotTriggerDecorator class is an implementation of the Trigger interface
 * that decorates another Trigger object by negating its evaluation result.
 * It returns the logical NOT of the decorated Trigger's evaluation.
 */
public class NotTriggerDecorator implements Trigger, Serializable {

    private final Trigger trigger;

    public NotTriggerDecorator(Trigger trigger) {
        this.trigger = trigger;
    }

    @Override
    public boolean evaluate() {
        return !trigger.evaluate();
    }

    @Override
    public String toString() {
        return "NOT " + trigger.toString();
    }
}