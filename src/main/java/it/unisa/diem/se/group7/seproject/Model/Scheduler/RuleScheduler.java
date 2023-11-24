package it.unisa.diem.se.group7.seproject.Model.Scheduler;

import it.unisa.diem.se.group7.seproject.Model.Rules.Rule;
import it.unisa.diem.se.group7.seproject.Model.Rules.RuleManager;
import javafx.collections.ObservableList;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RuleScheduler {

    private static final RuleScheduler INSTANCE = new RuleScheduler();
    private final ScheduledExecutorService scheduler;
    private final RuleManager ruleManager = RuleManager.getInstance();

    private RuleScheduler() {
        this.scheduler = Executors.newScheduledThreadPool(1);
    }

    public static RuleScheduler getInstance() {
        return INSTANCE;
    }

    public void startScheduler() {
        // Schedule the task to run every 1 minute
        scheduler.scheduleAtFixedRate(this::evaluateRules, 0, 5, TimeUnit.SECONDS);
    }

    private void evaluateRules() {
        System.out.println("Evaluating rules...");
        ObservableList<Rule> rules = ruleManager.getRules();

        for (Rule rule : rules) {
            if (rule.evaluate()) {
                rule.execute();
            }
        }
    }

    public void stopScheduler() {
        scheduler.shutdown();
    }
}
