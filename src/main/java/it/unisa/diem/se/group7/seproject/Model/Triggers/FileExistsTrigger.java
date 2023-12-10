package it.unisa.diem.se.group7.seproject.Model.Triggers;

import java.io.File;
import java.io.Serializable;

/**
 * FileExistsTrigger is a class that implements the Trigger interface. It represents a trigger that evaluates to true if a specified file exists in a given folder.
 * <p>
 * This class has a constructor that takes in the file name and the folder as parameters. It also overrides the evaluate() method from the Trigger interface to check if the file exists
 * in the folder.
 * The toString() method is overridden to provide a string representation of the trigger.
 * <p>
 * Example usage:
 * <pre>
 *   String file = "testFile.txt";
 *   File folder = new File("src/main/resources");
 *
 *   FileExistsTrigger fileExistsTrigger = new FileExistsTrigger(file, folder);
 *   boolean result = fileExistsTrigger.evaluate();
 *   String triggerDescription = fileExistsTrigger.toString();
 *
 *   System.out.println("Trigger result: " + result);
 *   System.out.println("Trigger description: " + triggerDescription);
 *   </pre>
 */
public class FileExistsTrigger implements Trigger, Serializable {
    private final String file;
    private final File folder;

    public FileExistsTrigger(String file, File folder) {
        this.file = file;
        this.folder = folder;
    }

    @Override
    public boolean evaluate() {
        File checkFile = new File(folder, file);
        return checkFile.exists();
    }

    @Override
    public String toString() {
        return "the file:\"" + file + "\" in the directory: \"" + folder + "\" exists";
    }
}
