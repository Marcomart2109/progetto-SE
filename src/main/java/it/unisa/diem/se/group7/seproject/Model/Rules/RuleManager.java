package it.unisa.diem.se.group7.seproject.Model.Rules;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collection;

public class RuleManager {
    private ObservableList<Rule> rules;
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

    public boolean removeAll(Collection<Rule> rules) {
        return this.rules.removeAll(rules);
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
