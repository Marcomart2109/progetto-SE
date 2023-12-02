package it.unisa.diem.se.group7.seproject.Model.Triggers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DayOfTheYearTriggerTest {

    DayOfTheYearTrigger trigger;

    @BeforeEach
    void setUp() {
         trigger = new DayOfTheYearTrigger();
    }

    @Test
    void evaluateShouldReturnTrueForMatchingDay() {
        // Choose a day and create a trigger for that day
        LocalDate specifiedDay = LocalDate.now();
        trigger.setSpecifiedDay(specifiedDay);


        // Evaluate the trigger
        assertTrue(trigger.evaluate());
    }

    @Test
    void evaluateShouldReturnFalseForNonMatchingDay() {
        // Choose a day and create a trigger for a different day
        LocalDate specifiedDay = LocalDate.now().plusDays(1);
        trigger.setSpecifiedDay(specifiedDay);

        // Evaluate the trigger
        assertFalse(trigger.evaluate());
    }

}