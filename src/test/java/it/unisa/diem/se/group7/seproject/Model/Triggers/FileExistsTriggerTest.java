package it.unisa.diem.se.group7.seproject.Model.Triggers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class FileExistsTriggerTest {

    File testFile;
    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary file for the dummy script
        File testFolder = new File("src/main/resources/");
        testFile = new File(testFolder, "testFile.txt");
        // Write the script content to the file
        Files.write(testFile.toPath(),
                "ciao".getBytes());

    }
    @Test
    public void testFileExistTrigger() {
        String file = "testFile.txt";
        File folder = new File ("src/main/resources");



        FileExistsTrigger fileExistsTrigger = new FileExistsTrigger(file, folder);
        assertTrue(fileExistsTrigger.evaluate());


    }
    @Test
    public void testFileNotExistsTrigger(){
        String file = "testFileNotExists.txt";
        File folder = new File("src/main/resources");

        FileExistsTrigger fileNotExistsTrigger = new FileExistsTrigger(file, folder);
        assertFalse(fileNotExistsTrigger.evaluate(),"error, file not exists");
    }
    @AfterEach
    public void cleanUp(){
        testFile.delete();
    }

}