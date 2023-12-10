package it.unisa.diem.se.group7.seproject.Model.Triggers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class CompositeAndTriggerTest {

    private Trigger t1, t2, t3;

    private File correctFile;

    private CompositeAndTrigger cat;

    @Before
    public void setUp1(){

        // t1 is a trigger whose evaluate() methods returns (for sure) true as value
        t1 = new TimeTrigger(LocalTime.now().getHour(), LocalTime.now().getMinute());

        correctFile = new File("src/main/resources/correctFile.txt");

        try{
            correctFile.createNewFile();
        }catch(Exception ignored){}

        File folder = new File("src/main/resources");

        // t2 is a trigger whose evaluate() methods returns (for sure) true as value
        t2 = new FileExistsTrigger(correctFile.getName(), folder);

        File wrongFile = new File("src/main/resources/wrongFile.txt");

        // t3 is a trigger whose evaluate() methods returns (for sure) false as value
        t3 = new FileExistsTrigger(wrongFile.getName(), folder);

        cat = new CompositeAndTrigger();

    }

    @Test
    public void testAddTrigger(){
        //test the impossibility of adding more than two triggers in the list
        assertThrows(RuntimeException.class, () ->{
            cat.add(t1);
            cat.add(t2);
            cat.add(t3);
        });
        cat.getTriggers().clear();

        //test the impossibility of adding the same trigger twice
        assertThrows(RuntimeException.class, () ->{
            cat.add(t1);
            cat.add(t1);
        });
        cat.getTriggers().clear();

        assertAll(() ->{
            cat.add(t1);
            cat.add(t2);
        });
    }

    @Test
    public void testRemoveTrigger(){
        cat.add(t1);

        assertThrows(RuntimeException.class, () -> cat.remove(t2));

        assertAll(() -> cat.remove(t1));

        cat.add(t1);
        cat.remove(t1);
        assertFalse(cat.getTriggers().contains(t1));
    }

    @Test
    public void testGetTriggers(){
        assertTrue(cat.getTriggers().isEmpty());

        cat.add(t1);
        assertFalse(cat.getTriggers().isEmpty());

        cat.add(t2);
        assertTrue(cat.getTriggers().contains(t1) && cat.getTriggers().contains(t2));
    }

    @Test
    public void testEvaluateTrue(){
        cat.add(t1);
        cat.add(t2);

        assertTrue(cat.evaluate());
    }

    @Test
    public void testEvaluateFalse(){
        cat.add(t1);
        cat.add(t3);

        assertFalse(cat.evaluate());
    }

    @After
    public void cleanUp(){
        correctFile.delete();
    }

}