package it.unisa.diem.se.group7.seproject.Model.Triggers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class CompositeOrTriggerTest {

    private Trigger t1, t2, t3, t4;

    private File correctFile;

    private CompositeOrTrigger cot;

    @Before
    public void setUp() {

        // t1 is a trigger whose evaluate() method returns (for sure) true as value
        t1 = new TimeTrigger(LocalTime.now().getHour(), LocalTime.now().getMinute());

        correctFile = new File("src/main/resources/correctFile.txt");

        try {
            correctFile.createNewFile();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        File folder = new File("src/main/resources");

        // t2 is a trigger whose evaluate() method returns (for sure) true as value
        t2 = new FileExistsTrigger(correctFile.getName(), folder);

        File wrongFile = new File("src/main/resources/wrongFile.txt");

        // t3 is a trigger whose evaluate() method returns (for sure) false as value
        t3 = new FileExistsTrigger(wrongFile.getName(), folder);

        // t4 is a trigger whose evaluate() method returns (for sure) false as value
        t4 = new TimeTrigger(LocalTime.now().plusHours(1).getHour(), LocalTime.now().getMinute());

        cot = new CompositeOrTrigger();
    }

    @Test
    public void testAddTrigger() {
        // Test the impossibility of adding more than two triggers in the list
        assertThrows(RuntimeException.class, () -> {
            cot.add(t1);
            cot.add(t2);
            cot.add(t3);
        });
        cot.getTriggers().clear();

        // Test the impossibility of adding the same trigger twice
        assertThrows(RuntimeException.class, () -> {
            cot.add(t1);
            cot.add(t1);
        });
        cot.getTriggers().clear();

        assertAll(() -> {
            cot.add(t1);
            cot.add(t2);
        });
    }

    @Test
    public void testRemoveTrigger() {
        cot.add(t1);

        assertThrows(RuntimeException.class, () -> cot.remove(t2));

        assertAll(() -> cot.remove(t1));

        cot.add(t1);
        cot.remove(t1);
        assertFalse(cot.getTriggers().contains(t1));
    }

    @Test
    public void testGetTriggers() {
        assertTrue(cot.getTriggers().isEmpty());

        cot.add(t1);
        assertFalse(cot.getTriggers().isEmpty());

        cot.add(t2);
        assertTrue(cot.getTriggers().contains(t1) && cot.getTriggers().contains(t2));
    }

    @Test
    public void testEvaluateTrue() {
        cot = new CompositeOrTrigger();
        cot.add(t1);
        cot.add(t2);

        assertTrue(cot.evaluate());
    }

    @Test
    public void testEvaluateFalse() {
        cot = new CompositeOrTrigger();
        cot.add(t3);
        cot.add(t4);

        assertFalse(cot.evaluate());
    }

    @After
    public void cleanUp() {
        correctFile.delete();
    }
}