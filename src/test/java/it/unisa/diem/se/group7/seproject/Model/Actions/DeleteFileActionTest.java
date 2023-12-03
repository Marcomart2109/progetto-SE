package it.unisa.diem.se.group7.seproject.Model.Actions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

class DeleteFileActionTest {
    private File testFile;
    private File testFolder;

   @BeforeEach
    void setUp() {

    testFolder =new File("src/main/resources/");
    testFile = new File(testFolder, "testFile.txt");
    }

    @Test
    void testDeleteFileSuccess() {
    DeleteFileAction deleteFileAction= new DeleteFileAction(testFile, testFolder);

    deleteFileAction.execute();
    //check file is eliminated
        assertFalse(testFile.exists());
    }
    @Test
    //test per vedere che il file non presente non venga eliminato
    void testDeleteFileSuccessFail(){
        File Folder2 = new File("src/main/test/");

        DeleteFileAction deleteFileAction= new DeleteFileAction(testFile, Folder2);

        deleteFileAction.execute();

        assertTrue(testFile.exists());
    }
}