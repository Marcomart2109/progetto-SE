package it.unisa.diem.se.group7.seproject.Model.Triggers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotTriggerDecoratorTest {
    private static class RealTrigger implements Trigger {
        private boolean evaluationResult;

        public RealTrigger(boolean evaluationResult) {
            this.evaluationResult = evaluationResult;
        }

        @Override
        public boolean evaluate() {
            return evaluationResult;
        }

        @Override
        public TriggerType getTYPE() {
            return null;
        }
    }

    @Test
    void testEvaluateWithTrueTrigger() {

        Trigger trueTrigger = new RealTrigger(true);
        NotTriggerDecorator notTriggerDecorator = new NotTriggerDecorator(trueTrigger);

        assertFalse(notTriggerDecorator.evaluate());
    }

    @Test
    void testEvaluateWithFalseTrigger() {

        Trigger falseTrigger = new RealTrigger(false);
        NotTriggerDecorator notTriggerDecorator = new NotTriggerDecorator(falseTrigger);

        assertTrue(notTriggerDecorator.evaluate());
    }

}