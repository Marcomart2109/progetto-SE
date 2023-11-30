package it.unisa.diem.se.group7.seproject.Model.Rules;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;

import java.time.Duration;
import java.time.LocalDateTime;

public class RuleSleepDecorator extends RuleDecorator {

    private boolean firedOnce;
    private boolean canFireAgain;
    private Duration sleepingPeriod;
    private LocalDateTime lastExecutionTime;

    public RuleSleepDecorator(Rule rule, String name, Trigger trigger, Action action, int days, int hours, int minutes) {
        super(rule, name, trigger, action);
        this.sleepingPeriod = Duration.ofDays(days).plusHours(hours).plusMinutes(minutes);
        firedOnce = false;
        canFireAgain = true; //By default, the rule can be fired again if sleeping period is set
    }
    @Override
    public boolean evaluate() {
        return getTrigger().evaluate();
    }

    @Override
    public void execute() {
        if (!firedOnce || (canFireAgain && isSleepingPeriodElapsed())) {
            getAction().execute();
            lastExecutionTime = LocalDateTime.now();
            firedOnce = true;
        }
    }

    private boolean isSleepingPeriodElapsed() {
        if (lastExecutionTime == null) {
            return true; // Rule hasn't been executed yet.
        }

        LocalDateTime nextAllowedExecutionTime = lastExecutionTime.plus(sleepingPeriod);
        return LocalDateTime.now().isAfter(nextAllowedExecutionTime);
    }

    // Additional methods to set sleeping period and toggle canFireAgain as needed

    public void setSleepingPeriod(Duration sleepingPeriod) {
        this.sleepingPeriod = sleepingPeriod;
    }

    public void setCanFireAgain(boolean canFireAgain) {
        this.canFireAgain = canFireAgain;
    }
}
