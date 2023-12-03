package it.unisa.diem.se.group7.seproject.Model;

import it.unisa.diem.se.group7.seproject.Model.Actions.NoFileFoundException;
import it.unisa.diem.se.group7.seproject.Model.Actions.PlayAudioAction;
import it.unisa.diem.se.group7.seproject.Model.Rules.Rule;
import it.unisa.diem.se.group7.seproject.Model.Rules.RuleBackup;
import it.unisa.diem.se.group7.seproject.Model.Rules.SimpleRule;
import it.unisa.diem.se.group7.seproject.Model.Triggers.TimeTrigger;
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
        File audioFile = new File("src/main/resources/file_example_WAV_1MG.wav");
        Rule rule = new SimpleRule("test rule", new TimeTrigger(21, 12), new PlayAudioAction(audioFile));
        testList.add(rule);
    }

    @Test
    public void testLoadFromBinaryFile(){
        RuleBackup.saveOnBinaryFile(testList, backupPath);

        testList.clear(); // when the application is closed, the list of rules is emptied

        assertThrows(NoFileFoundException.class, () -> {
            RuleBackup.loadFromBinaryFile(testList, wrongPath);
        });

        assertAll(()-> {
            RuleBackup.loadFromBinaryFile(testList, backupPath);
        });

        assertFalse(testList.isEmpty());   // when the application is launched, the list is populated
    }

    @Test
    public void testSaveOnBinaryFile() {
        assertThrows(NoFileFoundException.class, () -> {
            RuleBackup.saveOnBinaryFile(testList, wrongPath);
        });

        assertAll(()-> {
            RuleBackup.saveOnBinaryFile(testList, backupPath);
        });

    }

    @After
    public void cleanUp(){
        File testFile = new File(backupPath);
        testFile.delete();
    }

}