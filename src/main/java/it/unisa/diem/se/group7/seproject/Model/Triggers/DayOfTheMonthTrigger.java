package it.unisa.diem.se.group7.seproject.Model.Triggers;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DayOfTheMonthTrigger is a class that represents a trigger based on a specific day of the month.
 *
 * <p>
 * The trigger evaluates to true if the current day of the month matches the specified day.
 * </p>
 *
 * <p>
 * Example usage:
 * </p>
 * <pre>
 *     // Create a trigger for the current day of the month
 *     DayOfTheMonthTrigger trigger = new DayOfTheMonthTrigger();
 *
 *     // Set the day of the month
 *     trigger.setDayOfMonth(15);
 *
 *     // Evaluate the trigger
 *     boolean result = trigger.evaluate();
 * </pre>
 */
public class DayOfTheMonthTrigger implements Trigger, Serializable {
    private int day;

    public DayOfTheMonthTrigger() {
    }

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

    public void setDayOfMonth(int currentDayOfMonth) {
        this.day = currentDayOfMonth;
    }

    @Override
    public String toString() {
        return "the current day is " + day;
    }
}
