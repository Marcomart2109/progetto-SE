package it.unisa.diem.se.group7.seproject.Model;

import it.unisa.diem.se.group7.seproject.Model.Triggers.TimeTrigger;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class TimeTriggerTest {

    @Test
    public void testEvaluateFalse() {

        LocalTime currentTime = LocalTime.now().plusMinutes(10);
        // Ottieni solo l'ora e i minuti
        int currentHour = currentTime.getHour();
        int currentMinute = currentTime.getMinute();
        // Imposta un orario di attivazione futuro
        TimeTrigger timeTrigger = new TimeTrigger(currentHour, currentMinute);


        // Valuta il trigger e verifica che sia attivo
        assertFalse(timeTrigger.evaluate());
    }

    @Test
    public void testEvaluateTrue() {

        LocalTime currentTime = LocalTime.now();

        // Ottieni solo l'ora e i minuti
        int currentHour = currentTime.getHour();
        int currentMinute = currentTime.getMinute();
        // Imposta un orario di attivazione futuro
        TimeTrigger timeTrigger = new TimeTrigger(currentHour, currentMinute);


        // Valuta il trigger e verifica che sia attivo
        assertTrue(timeTrigger.evaluate());
    }
}