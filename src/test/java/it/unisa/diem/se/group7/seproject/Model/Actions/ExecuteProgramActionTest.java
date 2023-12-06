package it.unisa.diem.se.group7.seproject.Model.Actions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExecuteProgramActionTest {

    private ExecuteProgramAction executeProgramAction;
    private File dummyScript;

    @BeforeEach
    void setUp() throws IOException {
        // Set up common data for tests
        dummyScript = createDummyScript();
        String commandLineArguments = "--arg1 value1 --arg2 value2";
        executeProgramAction = new ExecuteProgramAction(dummyScript, commandLineArguments);
    }

    @AfterEach
    void tearDown() {
        // Delete the temporary script file
        if (dummyScript != null && dummyScript.exists()) {
            dummyScript.delete();
        }
    }

    @Test
    void constructorShouldInitializeArguments() {
        // Verify that the constructor initializes the arguments correctly
        List<String> expectedArguments = List.of("--arg1", "value1", "--arg2", "value2");
        assertEquals(expectedArguments, executeProgramAction.getArguments());
    }

    @Test
    void executeShouldNotThrowException() {
        // Verify that the execute method does not throw an exception
        assertDoesNotThrow(() -> executeProgramAction.execute());
    }


    private File createDummyScript() throws IOException {
        // Create a temporary file for the dummy script
        Path tempDirectory = Files.createTempDirectory("dummy-script-test");
        File scriptFile = new File(tempDirectory.toFile(), "dummy-script.sh");
        // Write the script content to the file
        Files.write(scriptFile.toPath(),
                "#!/bin/bash\necho 'Dummy external program is running'\necho \"Arguments: \\$1=\\$1, \\$2=\\$2, \\$3=\\$3\"\n".getBytes());

        // Make the script executable
        scriptFile.setExecutable(true);

        return scriptFile;
    }

}
