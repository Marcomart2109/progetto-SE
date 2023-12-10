package it.unisa.diem.se.group7.seproject.Model.Triggers;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * A {@code TimeTrigger} represents a trigger that activates at a specific time.
 * <p>
 * The activation time of the trigger is defined by the hour and minute specified during its construction.
 * The trigger evaluates to {@code true} when the current time matches the activation time, and {@code false} otherwise.
 * </p>
 * <p>
 * This class implements the {@code Trigger} interface and is serializable.
 * </p>
 */
public class TimeTrigger implements Trigger, Serializable {
    private LocalTime activationTime;

    public TimeTrigger(int hour, int minute) {
        this.activationTime = LocalTime.of(hour, minute);
    }

    public TimeTrigger() {
    }

    public LocalTime getActivationTime() {
        return activationTime;
    }

    @Override
    public boolean evaluate() {
        LocalTime currentTime = LocalTime.now();

        return activationTime.getHour() == currentTime.getHour() && activationTime.getMinute() == currentTime.getMinute();
    }

    @Override
    public String toString() {
        return "the current time is " + activationTime;
    }
}
