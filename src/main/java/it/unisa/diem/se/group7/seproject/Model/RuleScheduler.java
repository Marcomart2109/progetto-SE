package it.unisa.diem.se.group7.seproject.Model;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RuleScheduler {

    private final ScheduledExecutorService scheduler;
    private final List<Rule> rules;

    public RuleScheduler(List<Rule> rules) {
        this.rules = rules;
        this.scheduler = Executors.newScheduledThreadPool(1);
    }

    public void startScheduler() {
        // Schedule the task to run every 1 minute
        scheduler.scheduleAtFixedRate(this::valutaRegole, 0, 5, TimeUnit.SECONDS);
    }

    private void evaluateRules() {
        System.out.println("Evaluating rules...");

        for (Rule rule : rules) {
            if (rule.getTrigger().evaluate()) {
                System.out.println("Rule '" + rule.getName() + "' is triggered!");
                // Perform the action associated with the triggered rule
                rule.execute();
            }
        }
    }


    //public static void main(String[] args) {

        // Create a list of rules (Assuming you have a Rule class)
      //  List<Rule> rules = List.of(
           //     new Rule("Rule1", /* other parameters */),
             //   new Rule("Rule2", /* other parameters */),
                // Add more rules as needed
       // );

        // Create an instance of RuleScheduler
        //RuleScheduler ruleScheduler = new RuleScheduler(rules);

        // Start the scheduler
    //    ruleScheduler.startScheduler();
    //}

}
