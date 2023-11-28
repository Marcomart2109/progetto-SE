package it.unisa.diem.se.group7.seproject.Model.Actions;

import it.unisa.diem.se.group7.seproject.Model.Rules.Rule;
import it.unisa.diem.se.group7.seproject.Model.Rules.RuleBackup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class RuleBackupTest {
    private String backupPath, wrongPath;
    private ObservableList<Rule> testList;

    @Before
    public void setUp(){
        backupPath = "src/main/resources/testFile.bin";
        wrongPath = "";
        testList = FXCollections.observableArrayList();
    }

    @Test
    public void testLoadFromBinaryFile(){
        RuleBackup.saveOnBinaryFile(testList, backupPath);
        assertThrows(NoFileFoundException.class, () -> {
            RuleBackup.loadFromBinaryFile(testList, wrongPath);
        });
    }

    @Test
    public void testSaveOnBinaryFile() {
        assertThrows(NoFileFoundException.class, () -> {
            RuleBackup.saveOnBinaryFile(testList, wrongPath);
        });
    }

    @Test
    public void testNoExceptionThrown(){
        assertAll(()-> {
            RuleBackup.saveOnBinaryFile(testList, backupPath);
            RuleBackup.loadFromBinaryFile(testList, backupPath);
        });
    }

    @After
    public void cleanUp(){
        File testFile = new File(backupPath);
        testFile.delete();
    }

}