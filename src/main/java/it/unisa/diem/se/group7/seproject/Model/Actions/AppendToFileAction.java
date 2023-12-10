package it.unisa.diem.se.group7.seproject.Model.Actions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

/**
 * The AppendToFileAction class represents an action that appends text to a specified file.
 * It implements the Action interface and is Serializable.
 * <p>
 * Example usage:
 * AppendToFileAction appendAction = new AppendToFileAction(file, "Text to append");
 * appendAction.execute();
 *
 */
public class AppendToFileAction implements Action, Serializable {

    private File filePath;
    private String text;

    public String getText() {
        return text;
    }

    public AppendToFileAction(File filePath, String text) {
        if (validateFile(filePath)) {
            this.filePath = filePath;
            this.text = text;
        } else {
            System.err.println("File validation failed. Unable to create AppendToFileAction instance.");
        }
    }

    private boolean validateFile(File filePath) {
        if (filePath == null) {
            System.err.println("File is null.");
            return false;
        }

        // Check if the file exists
        if (!filePath.exists()) {
            System.err.println("File does not exist: " + filePath.getAbsolutePath());
            return false;
        }

        // Check if the file is writable
        if (!filePath.canWrite()) {
            System.err.println("Cannot write to the file: " + filePath.getAbsolutePath());
            return false;
        }

        return true;
    }

    @Override
    public void execute() {
        try (FileWriter fileWriter = new FileWriter(filePath,true)) {
            fileWriter.write(text);
            System.out.println("Text: " + text);
            System.out.println("Append to file: " + filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "append to the file: \"" + filePath + "\" the message: \"" + text + "\"";
    }
}

