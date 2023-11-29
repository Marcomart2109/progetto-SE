package it.unisa.diem.se.group7.seproject.Model.Actions;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class CopyFileAction implements Action, Serializable {
    File destDir; // destination directory
    File file;
    private final ActionType TYPE = ActionType.COPY_FILE;

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
    public ActionType getTYPE() {
        return TYPE;
    }

}
