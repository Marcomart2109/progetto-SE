package it.unisa.diem.se.group7.seproject.Model.Actions;

import it.unisa.diem.se.group7.seproject.Model.Rules.Rule;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void testSerializationDeserialization(){
        // the following code is useful to check the absence of exceptions during the serialization process
        PlayAudioAction serializedPaa = new PlayAudioAction(audioFile);
        assertAll(()-> {
            writePlayAudioActionObject(serializedPaa, backupPath);
        });

        // the following code is useful to check the absence of exceptions during the deserialization process
        assertAll(()-> {
            readPlayAudioActionObject(backupPath);
        });

        // the following code check if the object we want to serialize is equal to the deserialized one
        // according to the definition of the equals method in PlayAudioAudioAction class
        PlayAudioAction deserializedPaa = readPlayAudioActionObject(backupPath);
        assertEquals(serializedPaa, deserializedPaa);
    }

    private void writePlayAudioActionObject(PlayAudioAction paa, String backupPath){
        try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(backupPath)))){
            oos.writeObject(paa);
        }catch(Exception ex){
            throw new RuntimeException();
        }
    }

    private PlayAudioAction readPlayAudioActionObject(String backupPath){
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(backupPath)))) {
            return (PlayAudioAction) ois.readObject();
        }catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    @After
    public void cleanUp(){
        wrongFile.delete();

        File backupFile = new File(backupPath);
        backupFile.delete();
    }

}