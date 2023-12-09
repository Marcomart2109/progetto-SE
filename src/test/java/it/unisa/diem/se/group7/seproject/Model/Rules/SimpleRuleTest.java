package it.unisa.diem.se.group7.seproject.Model.Rules;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class SimpleRuleTest {

    private SimpleRule rule;
    private Trigger trigger;
    private Action action;

    // Concrete trigger that always evaluates to true
    private class TrueTrigger implements Trigger {
        public boolean evaluate() {
            return true;
        }
    }

    // Concrete action that prints a message to the console
    private class ConsoleAction implements Action {
        public void execute() {
            System.out.println("[Action triggered]");
        }
    }

    @BeforeEach
    void setUp() {
        trigger = new TrueTrigger();
        action = new ConsoleAction();
        rule = new SimpleRule("Test Rule", trigger, action);
    }

    @Test
    void testEvaluateNotFired() {
        rule.setActive(true);
        rule.setFired(false);
        // If the rule is not fired, active, and the condition is met, evaluate should return true
        assertTrue(rule.evaluate());
    }

    @Test
    void testEvaluateFired() {
        rule.setActive(true);
        rule.setFired(true);
        // If the rule is fired, regardless of whether the trigger condition is met, evaluate should return false
        assertFalse(rule.evaluate());
    }

    @Test
    void testExecute() {
        rule.setActive(true);
        rule.setFired(false);
        rule.execute();
        // After execution, the rule should be deactivated and fired
        assertFalse(rule.isActive());
        assertTrue(rule.isFired());
    }

    @Test
    void testSetFired() {
        rule.setFired(true);
        assertTrue(rule.isFired());
        rule.setFired(false);
        assertFalse(rule.isFired());
    }
}