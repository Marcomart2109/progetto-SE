package it.unisa.diem.se.group7.seproject.Model.Actions;

import java.io.File;

public class DeleteFileAction implements Action {

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
    public ActionType getTYPE() {
        return null;
    }
}
