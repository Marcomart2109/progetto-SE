package it.unisa.diem.se.group7.seproject.Model;

import java.time.LocalTime;

public class TimeTrigger implements Trigger {

    private LocalTime activationTime;


    /*
    public TimeTrigger(LocalTime activationTime) {
        this.activationTime = activationTime;
    }
    At this moment use construct with hour, minutes, second and millisecond because we don't know a type of activationTime
    */
    public TimeTrigger(int hour, int minute, int second, int millisecond) {
        this.activationTime = LocalTime.of(hour, minute, second, millisecond * 1_000_000);
    }

    public LocalTime getActivationTime() {
        return activationTime;
    }

    @Override
    public boolean evaluate() {
        LocalTime currentTime = java.time.LocalTime.now();

        return activationTime.equals(currentTime);
    }


}
