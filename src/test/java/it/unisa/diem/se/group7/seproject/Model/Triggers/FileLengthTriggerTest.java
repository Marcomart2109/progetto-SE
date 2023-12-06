package it.unisa.diem.se.group7.seproject.Model.Triggers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileLengthTriggerTest {

    @Test
    void testEvaluateFileExistsAndMeetsCondition() {
        String filePath = "src/main/resources/testfile.txt"; // Provide an existing file path
        long fileSize = 100; // Set the file size to a value that meets the condition

        FileLengthTrigger fileLengthTrigger = new FileLengthTrigger(fileSize, filePath);
        assertTrue(fileLengthTrigger.evaluate(), "File length meets the condition.");
    }

    @Test
    void testEvaluateFileExistsButDoesNotMeetCondition() {
        String filePath = "src/main/resources/testfile.txt"; // Provide an existing file path
        long fileSize = 10; // Set the file size to a value that does not meet the condition

        FileLengthTrigger fileLengthTrigger = new FileLengthTrigger(fileSize, filePath);
        assertFalse(fileLengthTrigger.evaluate(), "File length does not meet the condition.");
    }

    @Test
    void testEvaluateFileDoesNotExist() {
        String filePath = "nonexistentfile.txt"; // Provide a non-existing file path

        FileLengthTrigger fileLengthTrigger = new FileLengthTrigger(100, filePath);
        assertFalse(fileLengthTrigger.evaluate(), "File does not exist.");
    }

}