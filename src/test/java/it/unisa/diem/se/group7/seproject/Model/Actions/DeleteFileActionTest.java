package it.unisa.diem.se.group7.seproject.Model.Actions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteFileActionTest {
    File testFile;
        @BeforeEach
        void setUp() throws IOException {
            // Create a temporary file for the dummy script
            File testFolder = new File("src/main/resources/");
            testFile = new File(testFolder, "testFile.txt");
            // Write the script content to the file
            Files.write(testFile.toPath(),
                    "ciao".getBytes());

        }


    @Test
    void testDeleteExistingFile() {

        String testFileName = "testFile.txt";
        File testFolder = new File("src/main/resources/");


        File testFile = new File(testFolder, testFileName);



        assertTrue(testFile.exists(), "Test file does not exist before deletion");


        DeleteFileAction deleteFileAction = new DeleteFileAction(testFileName, testFolder);
        deleteFileAction.execute();


        assertFalse(testFile.exists(), "Test file still exists after deletion");
    }

    @Test
    void testDeleteNonExistingFile() {

        String nonExistingFileName = "nonExistingFile.txt";
        File testFolder = new File("main/java/resources/");


        assertFalse(new File(testFolder, nonExistingFileName).exists(), "Non-existing file exists before deletion");


        DeleteFileAction deleteFileAction = new DeleteFileAction(nonExistingFileName, testFolder);
        deleteFileAction.execute();


    }
}
