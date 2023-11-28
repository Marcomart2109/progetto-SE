package it.unisa.diem.se.group7.seproject.Model.Actions;

import it.unisa.diem.se.group7.seproject.Model.Rules.RuleBackup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayAudioActionTest {
    private String path, wrongPath;
    private File testFile, wrongFile;

    @Before
    public void setUp(){
        path = "src/main/resources/testFile.txt";
        testFile = new File(path);
        try{
            testFile.createNewFile();
        }catch(IOException exc){}

        wrongPath = "";
        wrongFile = new File(wrongPath);
    }

    @Test
    public void testInvalidAudioFile() {
        assertThrows(UnsupportedFileFormatException.class, () -> {
            PlayAudioAction paa = new PlayAudioAction(testFile);
        });
    }

    @Test
    public void testNoFileFound() {
        assertThrows(NoFileFoundException.class, () -> {
            PlayAudioAction paa = new PlayAudioAction(wrongFile);
        });
    }

    @After
    public void cleanUp(){
        testFile.delete();
    }

}