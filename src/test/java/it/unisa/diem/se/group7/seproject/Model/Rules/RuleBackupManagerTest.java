package it.unisa.diem.se.group7.seproject.Model.Rules;


import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

class RuleBackupManagerTest {

    @Test
    void isSingleton() {
        RuleBackupManager singleton = RuleBackupManager.getInstance();
        RuleBackupManager same = RuleBackupManager.getInstance();
        assertSame(singleton, same);
    }

}