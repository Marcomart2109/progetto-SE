package it.unisa.diem.se.group7.seproject.Model.Triggers;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DayOfTheYearTrigger is a class that implements the Trigger interface.
 * It represents a trigger that evaluates to true only on a specific day of the year.
 * <p>
 * The day of the year is determined by the LocalDate object specified during instantiation or by using the setSpecifiedDay method.
 * <p>
 * The evaluate method checks if the current date is equal to the specified day, and returns true if they match.
 * <p>
 * The toString method returns a string representation of the trigger, stating that the current day is the specified day.
 * <p>
 * Example usage:
 * <pre>
 * DayOfTheYearTrigger trigger = new DayOfTheYearTrigger(); // Create an instance of the trigger
 *
 * // Set the specified day using the setSpecifiedDay method
 * trigger.setSpecifiedDay(LocalDate.of(2022, 1, 1));
 *
 * // Evaluate the trigger
 * boolean result = trigger.evaluate();
 *
 * System.out.println(result); // Output: true if today is January 1, 2022, false otherwise
 * </pre>
 */
public class DayOfTheYearTrigger implements Trigger, Serializable {
    private LocalDate day;

    public DayOfTheYearTrigger() {
    }

    public DayOfTheYearTrigger(LocalDate day) {
        this.day = day;
    }

    @Override
    public boolean evaluate() {
        LocalDate currentDate = LocalDate.now();

        return currentDate.equals(day);
    }

    public void setSpecifiedDay(LocalDate specifiedDay) {
        this.day = specifiedDay;
    }

    @Override
    public String toString() {
        return "the current day is the " + day;
    }
}
