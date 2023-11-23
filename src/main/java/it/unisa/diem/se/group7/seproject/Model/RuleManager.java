package it.unisa.diem.se.group7.seproject.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RuleManager {
    private ObservableList<Rule> rules;
    private static RuleManager manager;

    private RuleManager() {
        this.rules = FXCollections.observableArrayList();
    }

    // Method to get rule manager singleton instance
    public static RuleManager getInstance() {
        if (manager == null) {
            manager = new RuleManager();
        }
        return manager;
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public boolean removeRule(Rule rule) {
        return rules.remove(rule);
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
}
