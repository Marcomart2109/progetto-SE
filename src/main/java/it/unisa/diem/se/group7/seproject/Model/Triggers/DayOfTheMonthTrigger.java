package it.unisa.diem.se.group7.seproject.Model.Triggers;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class DayOfTheMonthTrigger implements Trigger, Serializable {

    private final TriggerType TYPE = TriggerType.DAY_OF_MONTH;

    private int day;

    public DayOfTheMonthTrigger(int day) {
        this.day = day;
    }

    @Override
    public boolean evaluate() {
        // Get the current day of the month
        int currentDayOfMonth = LocalDate.now().getDayOfMonth();

        // Compare the current day with the specified day
        return currentDayOfMonth == day;
    }

    @Override
    public TriggerType getTYPE() {
        return TYPE;
    }
}
