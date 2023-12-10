package it.unisa.diem.se.group7.seproject.Model.Triggers;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * The DayOfTheWeekTrigger class is an implementation of the Trigger interface that triggers an event
 * only on a specific day of the week.
 *
 * <p>
 * Example usage:
 * <pre>
 *    // Create a trigger for the current day of the week
 *    DayOfTheWeekTrigger trigger = new DayOfTheWeekTrigger();
 *
 *    // Set the specific day of the week to trigger the event
 *    trigger.setDayOfWeek(DayOfWeek.MONDAY);
 *
 *    // Evaluate the trigger
 *    if (trigger.evaluate()) {
 *        // Execute the event
 *        performEvent();
 *    }
 * </pre>
 * </p>
 *
 * @see Trigger
 * @see DayOfWeek
 */
public class DayOfTheWeekTrigger implements Trigger, Serializable {
    private DayOfWeek day;

    public DayOfTheWeekTrigger() {
    }

    public DayOfTheWeekTrigger(DayOfWeek day) {
        this.day = day;
    }

    @Override
    public boolean evaluate() {
        // Get the current day of the week
        DayOfWeek currentDay = LocalDate.now().getDayOfWeek();
        // Compare the current day with the specified day
        return currentDay.equals(day);
    }

    public void setDayOfWeek(DayOfWeek currentDay) {
        this.day = currentDay;
    }

    @Override
    public String toString() {
        return "the current day of the week is " + day;
    }
}
