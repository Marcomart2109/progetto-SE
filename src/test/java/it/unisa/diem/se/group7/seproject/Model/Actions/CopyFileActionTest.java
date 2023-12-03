package it.unisa.diem.se.group7.seproject.Model.Actions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class CopyFileActionTest {
    private File origDir; // origin directory
    private File destDir; // destination directory
    private File origFile; // original file
    private CopyFileAction cfa;

    @Before
    public void setUp(){
        origDir = new File("src/main/resources/origin_directory");
        destDir = new File("src/main/resources/destination_directory");

        origDir.mkdir();
        destDir.mkdir();

        origFile = new File("src/main/resources/origin_directory/testFile.txt");
        try{
            origFile.createNewFile();
        }catch(IOException exc){}

        cfa = new CopyFileAction(destDir, origFile);
    }

    @Test
    public void testNoExceptionThrown(){
        assertAll(()-> {
            cfa.execute();
        });
    }

    @Test
    public void testFileCopiedWithSuccess(){
        String copyPath = destDir.getPath() + "/" + origFile.getName();

        File copyFile = new File(copyPath);

        cfa.execute();

        assertTrue(copyFile.isFile());
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