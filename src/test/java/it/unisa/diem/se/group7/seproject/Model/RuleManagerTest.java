package it.unisa.diem.se.group7.seproject.Model;

import it.unisa.diem.se.group7.seproject.Model.Actions.ShowDialogBoxAction;
import it.unisa.diem.se.group7.seproject.Model.Rules.Rule;
import it.unisa.diem.se.group7.seproject.Model.Rules.RuleManager;
import it.unisa.diem.se.group7.seproject.Model.Triggers.TimeTrigger;
import javafx.collections.ObservableList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;

import static org.junit.Assert.*;

class RuleManagerTest {

        private RuleManager ruleManager;
        Rule rule1;
        Rule rule2;

    @BeforeEach
    public void setUp() {
        // Inizializza RuleManager prima di ogni test
        ruleManager = RuleManager.getInstance();
        // Pulisci la lista delle regole prima di ogni test
        ruleManager.getRules().clear();
        rule1 = new Rule("prova1",new TimeTrigger(10,20), new ShowDialogBoxAction());
        rule2 = new Rule("prova2",new TimeTrigger(21,12), new ShowDialogBoxAction());
    }

        @Test
        public void testGetInstance() {
            RuleManager instance1 = RuleManager.getInstance();
            RuleManager instance2 = RuleManager.getInstance();
            assertSame("Le istanze devono essere le stesse", instance1, instance2);
        }

        @Test
        public void testAddRule() {

            ruleManager.addRule(rule1);

            assertTrue("La regola dovrebbe essere presente", ruleManager.getRules().contains(rule1));
        }

        @Test
        public void testRemoveRule() {

            ruleManager.addRule(rule1);
            boolean removed = ruleManager.removeRule(rule1);

            assertTrue("La regola dovrebbe essere rimossa", removed);
            assertFalse("La regola non dovrebbe essere più presente", ruleManager.getRules().contains(rule1));
        }

        @Test
        public void testGetRule() {

            ruleManager.addRule(rule1);
            Rule retrievedRule = ruleManager.getRule(0);

            assertNotNull("La regola dovrebbe essere recuperata", retrievedRule);
            assertSame("Le istanze devono essere le stesse", rule1, retrievedRule);
        }

        @Test
        public void testGetRules() {
            ObservableList<Rule> rules = ruleManager.getRules();
            assertNotNull("La lista delle regole non dovrebbe essere nulla", rules);
            assertEquals("La lista delle regole dovrebbe essere vuota inizialmente", 0, rules.size());


            ruleManager.addRule(rule1);
            ruleManager.addRule(rule2);

            assertEquals("La lista delle regole dovrebbe contenere 2 elementi", 2, rules.size());
            assertTrue("La lista delle regole dovrebbe contenere la regola 1", rules.contains(rule1));
            assertTrue("La lista delle regole dovrebbe contenere la regola 2", rules.contains(rule2));
        }
    }
