package it.unisa.diem.se.group7.seproject.Model.Actions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CopyFileActionTest {
    File origDir; // origin directory
    File destDir; // destination directory
    File testFile;
    @Before
    public void setUp(){
        origDir = new File("src/main/resources/origin_directory");
        destDir = new File("src/main/resources/destination_directory");

        origDir.mkdir();
        destDir.mkdir();

        testFile = new File("src/main/resources/origin_directory/testFile.txt");
        try{
            testFile.createNewFile();
        }catch(IOException exc){}
    }

    @Test
    public void testNoExceptionThrown(){
        assertAll(()-> {
            CopyFileAction cfa = new CopyFileAction(destDir, testFile);
            cfa.execute();
        });
    }

    @After
    public void cleanUp(){
        // deletion of files located into the origin directory
        for(File file : origDir.listFiles()){
            file.delete();
        }

        // deletion of files located into the destination directory
        for(File file : destDir.listFiles()){
            file.delete();
        }

        origDir.delete();
        destDir.delete();
    }

}