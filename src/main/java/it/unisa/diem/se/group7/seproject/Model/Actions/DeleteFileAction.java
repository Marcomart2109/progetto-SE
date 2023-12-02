package it.unisa.diem.se.group7.seproject.Model.Actions;

import java.io.File;

public class DeleteFileAction implements Action {

    private File file;
    private File folder;

    public DeleteFileAction(File file, File folder) {
        this.file = file;
        this.folder = folder;
    }

    @Override
    public void execute() {

        // get path complete of file
        String fullPath = file.getAbsolutePath();

        // get path complete of folder
        String folderPath = folder.getAbsolutePath();


        if (fullPath.startsWith(folderPath)) {
            System.out.println(file);
          if(file.delete()) {
              System.out.println("file is elimanated.");
          }else {
              System.out.println("file is not eliminated");
          }

             
        } else {
            System.out.println("File is not in the specifield folder.");
        }
    }
}
