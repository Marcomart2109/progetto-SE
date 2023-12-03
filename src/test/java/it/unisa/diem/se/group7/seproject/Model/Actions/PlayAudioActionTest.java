package it.unisa.diem.se.group7.seproject.Model.Actions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayAudioActionTest {
    private File wrongFile, absentFile, audioFile;
    private String backupPath;

    @Before
    public void setUp(){
        wrongFile = new File("src/main/resources/testFile.txt");
        try{
            wrongFile.createNewFile();
        }catch(IOException exc){}

        absentFile = new File("");

        audioFile = new File("src/main/resources/file_example_WAV_1MG.wav");

        backupPath = "src/main/resources/backupFile.bin";
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
/*
    @Test
    public void testPlayAudioActionSerialization(){
        PlayAudioAction paa = new PlayAudioAction(audioFile);
        assertAll(()-> {
            ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(backupPath)));
            oos.writeObject(paa);
        });
    }
    @Test
    public void testPlayAudioActionDeserialization(){
        assertAll(()-> {
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(backupPath)));
            PlayAudioAction paa = (PlayAudioAction) ois.readObject();
        });
    }

 */
    @Test
    public void testSerializationDeserialization(){
        // Serialization Test
        PlayAudioAction serializedPaa = new PlayAudioAction(audioFile);
        assertAll(()-> {
            ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(backupPath)));
            oos.writeObject(serializedPaa);
        });

        //Deserialization Test
        assertAll(()-> {
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(backupPath)));
            PlayAudioAction deserializedPaa = (PlayAudioAction) ois.readObject();
        });
    }

    @After
    public void cleanUp(){
        wrongFile.delete();

        File backupFile = new File(backupPath);
        backupFile.delete();
    }

}