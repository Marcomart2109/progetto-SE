package it.unisa.diem.se.group7.seproject.Model.Actions;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * The CopyFileAction class is an implementation of the Action interface. It represents an action that copies a file to a specified destination directory.
 * The class provides a constructor to set the destination directory and the file to be copied.
 * The execute method is used to perform the file copy operation. It uses the Files.copy method to copy the file to the destination directory.
 * <p>
 * Example usage:
 * File destDir = new File("path/to/destination/dir");
 * File file = new File("path/to/file/to/copy");
 * Action copyAction = new CopyFileAction(destDir, file);
 * copyAction.execute();
 *
 * @see Action
 */
public class CopyFileAction implements Action, Serializable {
    File destDir;
    File file;

    public CopyFileAction(File destDir, File file){
        this.destDir = destDir;
        this.file = file;
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


    @Override
    public String toString() {
        return "copy the file: \"" + file + "\" in the directory: \"" + destDir + "\"";
    }
}
