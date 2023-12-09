package it.unisa.diem.se.group7.seproject.Model.Rules;

import it.unisa.diem.se.group7.seproject.Model.Actions.ShowDialogBoxAction;
import it.unisa.diem.se.group7.seproject.Model.Rules.Rule;
import it.unisa.diem.se.group7.seproject.Model.Rules.RuleManager;
import it.unisa.diem.se.group7.seproject.Model.Rules.SimpleRule;
import it.unisa.diem.se.group7.seproject.Model.Triggers.TimeTrigger;
import javafx.collections.ObservableList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;

import static org.junit.Assert.*;

class RuleManagerTest {
    private RuleManager ruleManager;
    private Rule rule1;
    private Rule rule2;

    @BeforeEach
    public void setUp() {
        // Instantiate the rule manager
        ruleManager = RuleManager.getInstance();

        // clear the list of rules managed by the rule manager
        ruleManager.getRules().clear();

        rule1 = new SimpleRule("test rule 1", new TimeTrigger(10,20), new ShowDialogBoxAction());
        rule2 = new SimpleRule("test rule 2", new TimeTrigger(21,12), new ShowDialogBoxAction());
    }

    @Test
    public void testGetInstance() {
        RuleManager instance1 = RuleManager.getInstance();
        RuleManager instance2 = RuleManager.getInstance();
        assertSame("The two instances must be the same", instance1, instance2);
    }

    @Test
    public void testAddRule() {
        ruleManager.addRule(rule1);
        assertTrue(ruleManager.getRules().contains(rule1));
    }

    @Test
    public void testRemoveRule() {
        ruleManager.addRule(rule1);
        boolean removed = ruleManager.removeRule(rule1);

        assertTrue("The rule has not been removed from the list", removed);
        assertFalse("The rule is still contained in the list", ruleManager.getRules().contains(rule1));
    }

    @Test
    public void testGetRule() {
        ruleManager.addRule(rule1);
        Rule retrievedRule = ruleManager.getRule(0);

        assertNotNull("No rule has been retrieved", retrievedRule);
        assertSame("The retrieved rule differs from the expected one", rule1, retrievedRule);
    }

    @Test
    public void testGetRules() {
        ObservableList<Rule> rules = ruleManager.getRules();

        assertNotNull("The retrieved rules list is null", rules);
        assertTrue("At first, the list of rules must be empty", rules.isEmpty());


        ruleManager.addRule(rule1);
        ruleManager.addRule(rule2);

        assertTrue("Not all the added rules are contained in the list", rules.contains(rule1) && rules.contains(rule2));
    }

    @Test
    public void testDeactivateRule(){
        ruleManager.addRule(rule1);
        ruleManager.addRule(rule2);

        ruleManager.deactivateRule(rule1);
        assertFalse("The specified rule has not been deactivated at all", rule1.isActive());
        assertTrue("The specified rule should be active", rule2.isActive());
    }

    @Test
    public void testActivateRule(){
        rule1.setActive(false);
        ruleManager.addRule(rule1);

        ruleManager.activateRule(rule1);
        assertTrue("The specified rule should be active", rule1.isActive());
    }

}

