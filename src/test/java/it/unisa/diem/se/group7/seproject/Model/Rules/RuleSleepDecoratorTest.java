package it.unisa.diem.se.group7.seproject.Model.Rules;


import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

class RuleSleepDecoratorTest {

    private RuleSleepDecorator rule;

    // Concrete trigger that always evaluates to true
    private class TrueTrigger implements Trigger {
        public boolean evaluate() { return true; }
    }

    // Concrete action that does nothing
    private class EmptyAction implements Action {
        public void execute() { }
    }

    @BeforeEach
    public void setUp() {
        Action action = new EmptyAction();
        Trigger trigger = new TrueTrigger();
        Rule basicRule = new SimpleRule("Basic rule", trigger, action);
        rule = new RuleSleepDecorator(basicRule, 0, 0, 1);
    }

    @Test
    public void testEvaluateBeforeFiring() {
        // If not fired yet, it should be able to evaluate to true
        assertTrue(rule.evaluate());
    }

    @Test
    public void testEvaluateAfterFiring() throws InterruptedException {
        // After firing, it should not be able to evaluate to true again immediately
        rule.execute();
        assertFalse(rule.evaluate());
    }

    @Test
    public void testEvaluateAfterSleepingPeriodChange() {
        // Force the sleeping period to 0
        rule.setSleepingPeriod(Duration.ZERO);
        // Now it should be able to evaluate to true again, because it canFireAgain
        assertTrue(rule.evaluate());
    }

    @Test
    public void testEvaluateAfterExecutingAgain() throws InterruptedException {
        // After executing once more, it shouldn't evaluate to true anymore
        rule.execute();
        assertFalse(rule.evaluate());
    }


    @Test
    void testExecuteTwiceWithSleepingPeriod() throws InterruptedException {
        rule.setSleepingPeriod(Duration.ofMillis(50));
        rule.execute();

        // After executing, it should not evaluate to true immediately
        assertFalse(rule.evaluate());

        // Wait for the sleeping period to pass
        Thread.sleep(rule.getSleepingPeriod().toMillis() + 100);

        // Now it should be able to evaluate to true again
        assertTrue(rule.evaluate());
    }

    @Test
    void testExecuteTwiceWithoutSleepingPeriod() {

        rule.setSleepingPeriod(Duration.ZERO);

        rule.execute();

        // After executing, it should be able to evaluate to true immediately
        assertTrue(rule.evaluate());
    }


}