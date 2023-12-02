package it.unisa.diem.se.group7.seproject.Model.Triggers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DayOfWeekTriggerTest {

    DayOfWeekTrigger trigger;
    @BeforeEach
    void setUp() {
        trigger = new DayOfWeekTrigger();
    }

    @Test
    void evaluateShouldReturnTrueForMatchingDay() {
        DayOfWeek currentDay = LocalDate.now().getDayOfWeek();
        trigger.setDayOfWeek(currentDay);
        // Evaluate the trigger
        assertTrue(trigger.evaluate());
    }

    @Test
    void evaluateShouldReturnFalseForNonMatchingDay() {
        DayOfWeek notCurrentDay = LocalDate.now().plusDays(1).getDayOfWeek();

        DayOfWeekTrigger trigger = new DayOfWeekTrigger(notCurrentDay);
        // Evaluate the trigger
        assertFalse(trigger.evaluate());
    }
}