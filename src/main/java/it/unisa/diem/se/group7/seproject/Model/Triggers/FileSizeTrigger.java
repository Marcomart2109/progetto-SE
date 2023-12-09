package it.unisa.diem.se.group7.seproject.Model.Triggers;

import java.io.File;
import java.io.Serializable;

public class FileSizeTrigger implements Trigger, Serializable {

    private long fileSize;
    private String filePath;


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
        return "IF the size of the file: \"" + filePath + "\" is greater than " + fileSize;
    }
}
