package it.unisa.diem.se.group7.seproject.Model.Triggers;

import java.io.File;
import java.io.Serializable;

public class FileLengthTrigger implements Trigger, Serializable {

    private long fileSize;
    private String filePath;

    private TriggerType TYPE = TriggerType.FILE_SIZE;

    public FileLengthTrigger(long fileSize, String filePath) {
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
        System.out.println(" actualfilesize " + actualFileSize + " filesize " + fileSize);
        return actualFileSize <= fileSize;
    }

    @Override
    public TriggerType getTYPE() {
        return TYPE;
    }
}
