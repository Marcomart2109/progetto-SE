package it.unisa.diem.se.group7.seproject.Model.Rules;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collection;

/**
 * The RuleManager class is responsible for managing a collection of Rule objects.
 * It provides methods to add, remove, and retrieve rules from the collection.
 * RuleManager is implemented as a singleton class.
 * <p>
 * Usage:
 * RuleManager ruleManager = RuleManager.getInstance();
 * ruleManager.addRule(rule);
 * ruleManager.removeRule(rule);
 * Rule retrievedRule = ruleManager.getRule(index);
 * ObservableList<Rule> rules = ruleManager.getRules();
 * ruleManager.activateRule(rule);
 * ruleManager.deactivateRule(rule);
 */
public class RuleManager {
    private final ObservableList<Rule> rules;
    private static final RuleManager INSTANCE = new RuleManager();

    private RuleManager() {
        this.rules = FXCollections.observableArrayList();
    }

    // Method to get rule manager singleton instance
    public static RuleManager getInstance() {
        return INSTANCE;
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public boolean removeRule(Rule rule) {
        return rules.remove(rule);
    }

    public void removeAll(Collection<Rule> rules) {
        this.rules.removeAll(rules);
    }

    public Rule getRule(int index) {
        if (index >= 0 && index < rules.size()) {
            return rules.get(index);
        } else {
            return null;
        }
    }

    public ObservableList<Rule> getRules() {
        return rules;
    }

    public void activateRule(Rule rule){
        if(this.rules.contains(rule) && !rule.isActive()){
            rule.setActive(true);
        }
    }

    public void deactivateRule(Rule rule){
        if(this.rules.contains(rule) && rule.isActive()){
            rule.setActive(false);
        }
    }

}
