package it.unisa.diem.se.group7.seproject.Model.Actions;

import java.io.File;
import java.io.Serializable;

public class DeleteFileAction implements Action, Serializable {

    private String file;
    private File folder;

    public DeleteFileAction(String file, File folder) {
        this.file = file;
        this.folder = folder;
    }

    @Override
    public void execute() {

        File checkFile = new File(folder, file);

        if (checkFile.exists()) {
            checkFile.delete();
            System.out.println(" File " + file + " is elimanated");
        } else {
            System.out.println("File not exist");
        }

    }


    @Override
    public String toString() {
        return "THEN delete the file: \"" + file + "\" in the directory: \"" + folder + "\"";
    }
}
