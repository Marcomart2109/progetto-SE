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
    private File wrongFile, absentFile, audioFile;

    @Before
    public void setUp(){
        wrongFile = new File("src/main/resources/testFile.txt");
        try{
            wrongFile.createNewFile();
        }catch(IOException exc){}

        absentFile = new File("");

        audioFile = new File("src/main/resources/file_example_WAV_1MG.wav");
    }

    @Test
    public void testInvalidAudioFile() {
        assertThrows(UnsupportedFileFormatException.class, () -> {
            PlayAudioAction paa = new PlayAudioAction(wrongFile);
        });
    }

    @Test
    public void testNoFileFound() {
        assertThrows(NoFileFoundException.class, () -> {
            PlayAudioAction paa = new PlayAudioAction(absentFile);
        });
    }

    @Test
    public void testNoExceptionThrown(){
        assertAll(()-> {
            PlayAudioAction paa = new PlayAudioAction(audioFile);
        });
    }

    @After
    public void cleanUp(){
        wrongFile.delete();
    }

}