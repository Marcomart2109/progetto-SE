package it.unisa.diem.se.group7.seproject.Model.Triggers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileSizeTriggerTest {

    @TempDir
    Path tempDir;
    private Path testFilePath;

    @BeforeEach
    void setup() throws IOException {
        // Create a file
        testFilePath = tempDir.resolve("testfile.txt");
        Files.createFile(testFilePath);
        // Write some data into it
        Files.writeString(testFilePath, "Hello, world!");
    }

    @Test
    void testEvaluateFileExistsAndMeetsCondition() {
        long fileSize = 0; // We choose the exact file size

        FileSizeTrigger fileLengthTrigger = new FileSizeTrigger(fileSize, testFilePath.toString());
        assertTrue(fileLengthTrigger.evaluate(), "File length meets the condition.");
    }

    @Test
    void testEvaluateFileExistsButDoesNotMeetCondition() {
        long fileSize; // Set the file size to a value that does not meet the condition
        try {
            fileSize = Files.size(testFilePath) + 1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FileSizeTrigger fileLengthTrigger = new FileSizeTrigger(fileSize, testFilePath.toString());
        assertFalse(fileLengthTrigger.evaluate(), "File length does not meet the condition.");
    }

    @Test
    void testEvaluateFileDoesNotExist() {
        Path nonexistentFilePath = tempDir.resolve("nonexistentfile.txt"); // Provide paths of non-existent file

        FileSizeTrigger fileLengthTrigger = new FileSizeTrigger(100, nonexistentFilePath.toString());
        assertFalse(fileLengthTrigger.evaluate(), "File does not exist.");
    }

}