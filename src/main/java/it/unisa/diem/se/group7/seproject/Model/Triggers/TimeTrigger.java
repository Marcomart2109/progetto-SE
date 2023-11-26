package it.unisa.diem.se.group7.seproject.Model.Triggers;

import java.io.Serializable;
import java.time.LocalTime;

public class TimeTrigger implements Trigger, Serializable {
    /*
    public TimeTrigger(LocalTime activationTime) {
        this.activationTime = activationTime;
    }
    At this moment use construct with hour, minutes, second and millisecond because we don't know a type of activationTime
    */
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

        // Confronta solo ore e minuti
        return activationTime.getHour() == currentTime.getHour() && activationTime.getMinute() == currentTime.getMinute();
    }


}
