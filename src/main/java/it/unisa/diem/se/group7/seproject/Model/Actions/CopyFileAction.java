package it.unisa.diem.se.group7.seproject.Model.Actions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class CopyFileAction implements Action{
    File destDir; // destination directory
    File file;

    public CopyFileAction(File destDir, File file){
        if(this.validateDir(destDir) && this.validateFile(file)){
            this.destDir = destDir;
            this.file = file;
        }
    }

    @Override
    public void execute(){
        // origin path of the chosen file
        Path origPath = Paths.get(this.file.getPath());
        // path of the copy
        Path destPath = Paths.get(this.destDir.getPath() + "/" + file.getName());

        try {
            Files.copy(origPath, destPath, REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Error occurred during the copy of the chosen file!");
        }
    }

    // The following method check if the object file is really a file and if it is placed in the directory dir.
    private boolean validateFile(File file){
        if(!file.isFile()){
            System.err.println(file.getPath() + " is not a file!");
            return false;
        }

        return true;
    }

    private boolean validateDir(File dir){
        boolean isDir = dir.isDirectory();
        if(!isDir){
            System.err.println(dir.getPath() + " is not a directory!");
        }
        return isDir;
    }

}
