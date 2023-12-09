package it.unisa.diem.se.group7.seproject.Model.Actions;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class CompositeActionTest {
    private Action a1, a2, a3;
    private File testFile;

    private File folder;
    private CompositeAction compositeAction;

    @BeforeEach
    public void setUp(){
        compositeAction = new CompositeAction();
        a1 = new TrueAction();
        a2= new FalseAction();
        a3 = new FalseAction();
    }
    public class TrueAction implements Action{
        public void execute(){
            System.out.println("True");
       }
    }
    public class FalseAction implements Action{
        public void execute(){
            System.out.println("False");
        }
    }
    @Test
    void addAction() {
        // Test the impossibility of adding the same action twice
        assertThrows(RuntimeException.class, () -> {
            compositeAction.add(a2);
            compositeAction.add(a2);
        });

        // Test adding two distinct actions without exceptions
        assertDoesNotThrow(() -> {
            compositeAction.add(a1);
            compositeAction.add(a2);
        });
    }



    @Test
    void removeAction() {
        compositeAction.add(a1);

        assertThrows(RuntimeException.class,() -> {
            compositeAction.remove(a3);
        });
        assertDoesNotThrow(() -> {
            compositeAction.remove(a1);
        });
        compositeAction.add(a2);
        compositeAction.remove(a2);
        assertFalse(compositeAction.getActions().contains(a2));
    }
    @Test
    void getActions() {
        assertTrue(compositeAction.getActions().isEmpty());
        compositeAction.add(a1);
        assertFalse(compositeAction.getActions().isEmpty());
        compositeAction.add(a3);
        assertTrue(compositeAction.getActions().contains(a1) && compositeAction.getActions().contains(a3));
    }

    @Test
    void execute() {
        compositeAction = new CompositeAction();
        compositeAction.add(a1);
        compositeAction.add(a2);
        compositeAction.execute();
    }
}