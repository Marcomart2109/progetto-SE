package it.unisa.diem.se.group7.seproject.Model.Triggers;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class DayOfWeekTrigger implements Trigger, Serializable {

    private final TriggerType TYPE = TriggerType.DAY_OF_WEEK;

    private DayOfWeek day;

    public DayOfWeekTrigger(DayOfWeek day) {
        this.day = day;
    }

    @Override
    public boolean evaluate() {
        // Get the current day of the week
        DayOfWeek currentDay = LocalDate.now().getDayOfWeek();
        // Compare the current day with the specified day
        return currentDay.equals(day);
    }

    @Override
    public TriggerType getTYPE() {
        return TYPE;
    }
}
