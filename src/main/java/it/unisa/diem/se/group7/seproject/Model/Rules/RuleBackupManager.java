package it.unisa.diem.se.group7.seproject.Model.Rules;

import it.unisa.diem.se.group7.seproject.Model.Actions.NoFileFoundException;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The RuleBackupManager class handles the saving and loading of Rule objects to and from a binary file.
 * It uses a singleton pattern to ensure that only one instance of RuleBackupManager is created.
 * The default backup path is "src/main/resources/" and the default backup file is "backupFile.bin".
 * The RuleBackupManager class provides methods to save a list of Rule objects to the binary file and load them back.
 * If the binary file does not exist, it creates a new file.
 * If the binary file is empty, it prints a message saying that the backup file is empty.
 * The RuleBackupManager class also throws a NoFileFoundException if the binary file is not found.
 * The RuleBackupManager class uses the Rule and NoFileFoundException classes.
 */
public class RuleBackupManager {
    public static final String DEFAULT_BACKUP_PATH = "src/main/resources/";
    public static final String DEFAULT_BACKUP_FILE = "backupFile.bin";
    private static final RuleBackupManager INSTANCE = new RuleBackupManager();
    private final File backupFile;

    private RuleBackupManager(){
        backupFile = new File(DEFAULT_BACKUP_PATH, DEFAULT_BACKUP_FILE);
        createNewFile();
    }

    private RuleBackupManager(String backupPath, String backupFile) {
        this.backupFile = new File(backupPath, backupFile);
        createNewFile();
    }

    public static RuleBackupManager getInstance(){
        return INSTANCE;
    }

    private void createNewFile() {
        try {
            if(backupFile.createNewFile()) {
                System.out.println("Created backup file: " + this.backupFile.getPath());
            }
        } catch (IOException e) {
            throw new RuntimeException("An I/O error occurred during the creation of the backup file!");
        }
    }

    public void saveOnBinaryFile(ObservableList<Rule> list){
        List<Rule> newList = new ArrayList<>(list);
        try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(backupFile)))){
            oos.writeObject(newList);
        }catch(FileNotFoundException ex){
            throw new NoFileFoundException("Error during the opening or creation of the specified backup file");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void loadFromBinaryFile(ObservableList<Rule> list){

        if(isFileEmpty()) {
            System.out.println("Backup file is empty");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(backupFile)))) {
            List<Rule> newList = (ArrayList<Rule>) ois.readObject();
            list.addAll(newList);
        } catch (FileNotFoundException ex) {
            throw new NoFileFoundException("Backup file not found");
        } catch (ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
        }
    }

    private boolean isFileEmpty() {
        return backupFile.length() == 0;
    }


}
