package it.unisa.diem.se.group7.seproject.Model.Triggers;

/**
 * The `CompositeOrTrigger` class is a concrete implementation of the `CompositeTrigger` abstract class.
 * It represents a composite trigger that combines multiple triggers using the logical OR operator.
 * The trigger is considered true if any of the triggers it contains evaluate to true.
 */
public interface Trigger {
    boolean evaluate();
}
