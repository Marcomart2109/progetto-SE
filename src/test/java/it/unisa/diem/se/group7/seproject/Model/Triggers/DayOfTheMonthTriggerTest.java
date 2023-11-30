package it.unisa.diem.se.group7.seproject.Model.Triggers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DayOfTheMonthTriggerTest {

    DayOfTheMonthTrigger trigger;
    @BeforeEach
    void setUp() {
        trigger = new DayOfTheMonthTrigger();
    }

    @Test
    void evaluateShouldReturnTrueForMatchingDay() {
        // Choose a day and create a trigger for that day
        int currentDayOfMonth = LocalDate.now().getDayOfMonth();
        trigger.setDayOfMonth(currentDayOfMonth);

        // Evaluate the trigger
        assertTrue(trigger.evaluate());
    }

    @Test
    void evaluateShouldReturnFalseForNonMatchingDay() {
        // Choose a day and create a trigger for a different day
        int currentDayOfMonth = LocalDate.now().plusDays(1).getDayOfMonth();

        DayOfTheMonthTrigger trigger = new DayOfTheMonthTrigger(currentDayOfMonth);
        // Evaluate the trigger
        assertFalse(trigger.evaluate());
    }
}