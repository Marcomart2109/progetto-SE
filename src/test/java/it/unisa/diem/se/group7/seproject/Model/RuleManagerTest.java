package it.unisa.diem.se.group7.seproject.Model;

import javafx.collections.ObservableList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class RuleManagerTest {

        private RuleManager ruleManager;

    @BeforeEach
    public void setUp() {
        // Inizializza RuleManager prima di ogni test
        ruleManager = RuleManager.getInstance();
        // Pulisci la lista delle regole prima di ogni test
        ruleManager.getRules().clear();
    }

        @Test
        public void testGetInstance() {
            RuleManager instance1 = RuleManager.getInstance();
            RuleManager instance2 = RuleManager.getInstance();
            assertSame("Le istanze devono essere le stesse", instance1, instance2);
        }

        @Test
        public void testAddRule() {
            Rule rule = new Rule("prova4","TIME_TRIGGER","SHOW_DIALOG_BOX");

            ruleManager.addRule(rule);

            assertTrue("La regola dovrebbe essere presente", ruleManager.getRules().contains(rule));
        }

        @Test
        public void testRemoveRule() {
            Rule rule = new Rule("prova","TIME_TRIGGER","SHOW_DIALOG_BOX");

            ruleManager.addRule(rule);
            boolean removed = ruleManager.removeRule(rule);

            assertTrue("La regola dovrebbe essere rimossa", removed);
            assertFalse("La regola non dovrebbe essere più presente", ruleManager.getRules().contains(rule));
        }

        @Test
        public void testGetRule() {
            Rule rule = new Rule("prova","TIME_TRIGGER","SHOW_DIALOG_BOX");

            ruleManager.addRule(rule);
            Rule retrievedRule = ruleManager.getRule(0);

            assertNotNull("La regola dovrebbe essere recuperata", retrievedRule);
            assertSame("Le istanze devono essere le stesse", rule, retrievedRule);
        }

        @Test
        public void testGetRules() {
            ObservableList<Rule> rules = ruleManager.getRules();
            assertNotNull("La lista delle regole non dovrebbe essere nulla", rules);
            assertEquals("La lista delle regole dovrebbe essere vuota inizialmente", 0, rules.size());

            Rule rule1 = new Rule("prova","TIME_TRIGGER","SHOW_DIALOG_BOX");
            Rule rule2 = new Rule("prova2","TIME_TRIGGER","SHOW_DIALOG_BOX");

            ruleManager.addRule(rule1);
            ruleManager.addRule(rule2);

            assertEquals("La lista delle regole dovrebbe contenere 2 elementi", 2, rules.size());
            assertTrue("La lista delle regole dovrebbe contenere la regola 1", rules.contains(rule1));
            assertTrue("La lista delle regole dovrebbe contenere la regola 2", rules.contains(rule2));
        }
    }
