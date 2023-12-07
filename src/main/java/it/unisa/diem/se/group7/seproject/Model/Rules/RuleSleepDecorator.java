package it.unisa.diem.se.group7.seproject.Model.Rules;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;

public class RuleSleepDecorator extends RuleDecorator implements Serializable {
    private Rule rule;
    private boolean firedOnce;
    private boolean canFireAgain;
    private Duration sleepingPeriod;
    private LocalDateTime lastExecutionTime;

    public RuleSleepDecorator(Rule rule, int days, int hours, int minutes) {
        this.rule = rule;
        this.sleepingPeriod = Duration.ofDays(days).plusHours(hours).plusMinutes(minutes);
        firedOnce = false;
        canFireAgain = true; //By default, the rule can be fired again if sleeping period is set
    }

    @Override
    public boolean evaluate() {
        // Check if the trigger condition is met and if the sleeping period has elapsed
        boolean triggerConditionMet = rule.getTrigger().evaluate();

        if (!firedOnce && triggerConditionMet && isActive()) {
            System.out.println("Rule \"" + rule.getName() + "\" is triggered");
            return true;
        }

        if (firedOnce && canFireAgain && isSleepingPeriodElapsed() && isActive()) {
            System.out.println("Rule \"" + rule.getName() + "\" is triggered again after " + sleepingPeriod + " of sleeping period");
            canFireAgain = false;
            return true;
        }

        return false;
    }


    @Override
    public void execute() {
        rule.getAction().execute();
        lastExecutionTime = LocalDateTime.now();
        if(firedOnce) {
            setActive(false);
        }
        firedOnce = true;
    }

    private boolean isSleepingPeriodElapsed() {
        if (lastExecutionTime == null) {
            return true; // Rule hasn't been executed yet.
        }

        LocalDateTime now = LocalDateTime.now();
        Duration timeSinceLastExecution = Duration.between(lastExecutionTime, now);

        return timeSinceLastExecution.compareTo(sleepingPeriod) >= 0;
    }

    // Additional methods to set sleeping period and toggle canFireAgain as needed

    public void setSleepingPeriod(Duration sleepingPeriod) {
        this.sleepingPeriod = sleepingPeriod;
    }

    public void setCanFireAgain(boolean canFireAgain) {
        this.canFireAgain = canFireAgain;
    }

    @Override
    public String getName() {
        return rule.getName();
    }

    @Override
    public Action getAction() {
        return rule.getAction();
    }

    @Override
    public Trigger getTrigger() {
        return rule.getTrigger();
    }

    @Override
    public void setTrigger(Trigger trigger) {
        rule.setTrigger(trigger);
    }

    @Override
    public void setAction(Action action) {
        rule.setAction(action);
    }

    @Override
    public BooleanProperty activeProperty() {
        return rule.activeProperty();
    }

    @Override
    public void setActive(boolean active) {
        rule.setActive(active);
    }

    @Override
    public boolean isActive() {
        return rule.isActive();
    }

    @Override
    public void setName(String name) {
        rule.setName(name);
    }

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeBoolean(activeProperty().get()); // Write the active status
    }

    // Custom deserialization method
    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        boolean isActive = in.readBoolean(); // Read the active status
        setActiveProperty(new SimpleBooleanProperty(isActive));
    }
}
