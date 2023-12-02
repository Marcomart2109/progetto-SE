package it.unisa.diem.se.group7.seproject.Model.Rules;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;

import java.time.Duration;
import java.time.LocalDateTime;

public class RuleSleepDecorator extends RuleDecorator {
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
    public void setName(String name) {
        rule.setName(name);
    }
}
