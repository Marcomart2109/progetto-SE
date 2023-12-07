package it.unisa.diem.se.group7.seproject.Model.Triggers;

import java.io.File;

public class FileExistsTrigger implements Trigger{
    private String file;
    private File folder;

    public FileExistsTrigger(String file, File folder) {
        this.file = file;
        this.folder = folder;
    }

    @Override
    public boolean evaluate() {
        File checkFile = new File(folder, file);

        if (checkFile.exists()) {
             return true;
        } else {
           return false;
        }

    }

    @Override
    public TriggerType getTYPE() {
        return null;
    }

    @Override
    public String toString() {
        return "IF the file:\"" + file + "\" in the directory: \"" + folder + "\" exists";
    }
}
