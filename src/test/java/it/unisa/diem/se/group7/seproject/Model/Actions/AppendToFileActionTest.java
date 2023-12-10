package it.unisa.diem.se.group7.seproject.Model.Actions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AppendToFileActionTest {

    // Creating a dummy file for testing
    private File testFile;

    @BeforeEach
    public void setUp() throws IOException {
        // Creating the file path inside the src/main/resources/ directory
        String filePath = "src/main/resources/testFile.txt";
        testFile = new File(filePath);

        // Make sure the parent directory exists
        File parentDirectory = testFile.getParentFile();
        if (!parentDirectory.exists() && !parentDirectory.mkdirs()) {
            throw new IOException("Impossibile creare la directory: " + parentDirectory);
        }

        // Writing initial content to the file
        try (FileWriter fileWriter = new FileWriter(testFile)) {
            fileWriter.write("Contenuto iniziale del file, ");
        }
    }
    
    @Test
    public void testAppendToFile() {

        // Creating an instance of AppendToFileAction
        AppendToFileAction appendAction = new AppendToFileAction(testFile, " Prova test per AppendToFileAction");

        // Execution of the action
        appendAction.execute();

        // Verify that the file has been created
        assertTrue(testFile.exists());

        // Verify that text has been added to the file
        assertTrue(getFileContent(testFile).contains(appendAction.getText()));
    }

    private String getFileContent(File file) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }


    @AfterEach
    public void cleanUp(){
        testFile.delete();
    }

}