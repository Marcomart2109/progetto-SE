package it.unisa.diem.se.group7.seproject.Model.Triggers;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TimeTriggerTest {

    @Test
    public void testEvaluateFalse() {

        LocalTime currentTime = LocalTime.now().plusMinutes(10);
        // Get only the hour and minutes
        int currentHour = currentTime.getHour();
        int currentMinute = currentTime.getMinute();
        // Set a future activation time
        TimeTrigger timeTrigger = new TimeTrigger(currentHour, currentMinute);

        // Evaluate the trigger and verify that it is not active
        assertFalse(timeTrigger.evaluate());
    }

    @Test
    public void testEvaluateTrue() {

        LocalTime currentTime = LocalTime.now();

        // Get only the hour and minutes
        int currentHour = currentTime.getHour();
        int currentMinute = currentTime.getMinute();
        // Set a future activation time
        TimeTrigger timeTrigger = new TimeTrigger(currentHour, currentMinute);

        // Evaluate the trigger and verify that it is active
        assertTrue(timeTrigger.evaluate());
    }
}
