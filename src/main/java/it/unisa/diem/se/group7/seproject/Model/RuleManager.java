package it.unisa.diem.se.group7.seproject.Model;

import java.util.ArrayList;
import java.util.List;

public class RuleManager {
    private List<Rule> rules;
    private static RuleManager manager;

    private RuleManager(){
        this.rules = new ArrayList<>();
    }

   //metod to get rule menager singleton instance
    public static RuleManager getInstance(){
        if(manager== null){
            manager = new RuleManager();
        }
        return manager;
    }
    public void addRule(Rule rule){
        rules.add(rule);
    }
    public boolean removeRule(Rule rule){
        return rules.remove(rule);
    }
    public Rule getRule(int index){
        if(index>=0 && index< rules.size()){
            return rules.get(index);
        }
        else{
            return null;
        }
    }
}
