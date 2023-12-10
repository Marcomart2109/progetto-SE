package it.unisa.diem.se.group7.seproject.Model.Triggers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The `CompositeTrigger` class is an abstract class that serves as a base for composite trigger implementations.
 * It implements the `Trigger` interface and provides common functionality for managing a list of triggers.
 * It can be extended to create specific types of composite triggers.
 * <p>
 * The `CompositeTrigger` class has two constructors: one with no parameters and one that takes a variable number of triggers.
 * The first constructor initializes the `triggers` list as an empty ArrayList.
 * The second constructor initializes the `triggers` list with the given triggers.
 * <p>
 * The `add` method allows adding a trigger to the `triggers` list.
 * It checks if the trigger is already contained in the list and throws an exception if it is.
 * It also checks if the `triggers` list has reached the limit, which is 2 in this case, and throws an exception if it has.
 * If the trigger passes these checks, it is added to the `triggers` list.
 * <p>
 * The `remove` method allows removing a trigger from the `triggers` list.
 * If the trigger is not contained in the list, it throws an exception.
 * If the trigger is found in the list, it is removed.
 * <p>
 * The `getTriggers` method returns the `triggers` list.
 * <p>
 * The `evaluate` method is an abstract method that needs to be implemented by subclasses.
 * It is responsible for evaluating the composite trigger's conditions and returning a boolean result.
 * <p>
 * The `toString` method is an overridden method that returns a string representation of the composite trigger.
 * It returns a formatted string that shows the individual triggers contained in the composite trigger.
 */
public abstract class CompositeTrigger implements Trigger, Serializable {
    private final List<Trigger> triggers;
    private static final int TRIGGER_LIMIT = 2;

    public CompositeTrigger(){
        triggers = new ArrayList<>();
    }
    public CompositeTrigger(Trigger... triggers) {
        this.triggers = new ArrayList<>(Arrays.asList(triggers));
    }

    public void add(Trigger t){
        if(triggers.contains(t)){
            throw new RuntimeException("The specified trigger is already contained in the list!");
        }
        if(triggers.size() < TRIGGER_LIMIT){
            triggers.add(t);
        }else{
            throw new RuntimeException("The list already contains two triggers");
        }
    }

    public void remove(Trigger t){
        if(!triggers.remove(t)){
            throw new RuntimeException("The specified trigger is not contained in the list!");
        }
    }

    public List<Trigger> getTriggers(){
        return triggers;
    }

}
