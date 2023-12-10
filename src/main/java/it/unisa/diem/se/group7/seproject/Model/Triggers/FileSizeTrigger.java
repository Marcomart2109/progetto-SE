package it.unisa.diem.se.group7.seproject.Model.Triggers;

import java.io.File;
import java.io.Serializable;

/**
 * The {@code FileSizeTrigger} class implements the {@code Trigger} interface and represents a trigger condition
 * based on the size of a file.
 *
 * <p>
 * A file size trigger is evaluated by checking if the size of a given file is greater than the specified file size.
 * </p>
 *
 * <p>
 * The {@code FileSizeTrigger} class is serializable, allowing objects of this class to be serialized and
 * deserialized.
 * </p>
 *
 * <p>
 * Example usage:
 * </p>
 *
 * <pre>
 * long fileSize = 1000;
 * String filePath = "path/to/file.txt";
 *
 * FileSizeTrigger fileTrigger = new FileSizeTrigger(fileSize, filePath);
 * if (fileTrigger.evaluate()) {
 *     System.out.println("File size is greater than " + fileSize);
 * } else {
 *     System.out.println("File size is not greater than " + fileSize);
 * }
 * </pre>
 */
public class FileSizeTrigger implements Trigger, Serializable {
    private final long fileSize;
    private final String filePath;

    public FileSizeTrigger(long fileSize, String filePath) {
        this.fileSize = fileSize;
        this.filePath = filePath;
    }

    @Override
    public boolean evaluate() {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not found: " + filePath);
            return false;
        }
        long actualFileSize = file.length();
        return actualFileSize > fileSize;
    }

    @Override
    public String toString() {
        return "the size of the file: \"" + filePath + "\" is greater than " + fileSize;
    }
}
