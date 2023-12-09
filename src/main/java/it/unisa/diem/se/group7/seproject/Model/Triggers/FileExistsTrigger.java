package it.unisa.diem.se.group7.seproject.Model.Triggers;

import java.io.File;
import java.io.Serializable;

public class FileExistsTrigger implements Trigger, Serializable {
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
    public String toString() {
        return "IF the file:\"" + file + "\" in the directory: \"" + folder + "\" exists";
    }
}
