package it.unisa.diem.se.group7.seproject.Model.Actions;

import java.io.File;
import java.io.Serializable;

/**
 * The DeleteFileAction class represents an Action that deletes a file in a specified folder.
 * <p>
 * It implements the Action interface and is Serializable.
 * <p>
 * Usage Example:
 *     DeleteFileAction deleteFileAction = new DeleteFileAction("example.txt", new File("path/to/folder"));
 *     deleteFileAction.execute();
 * <p>
 * Note:
 *     Prior to deletion, the existence of the file is checked using the specified folder and file name.
 *
 * @see Action
 * @see Serializable
 */
public class DeleteFileAction implements Action, Serializable {
    private final String file;
    private final File folder;

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
        return "delete the file: \"" + file + "\" in the directory: \"" + folder + "\"";
    }
}
