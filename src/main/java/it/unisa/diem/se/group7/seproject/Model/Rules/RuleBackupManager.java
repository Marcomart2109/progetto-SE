package it.unisa.diem.se.group7.seproject.Model.Rules;

import it.unisa.diem.se.group7.seproject.Model.Actions.NoFileFoundException;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RuleBackupManager {
    private final static String BACKUP_PATH = "src/main/resources/backupFile.bin";
    private static final RuleBackupManager INSTANCE = new RuleBackupManager();
    private static File backupFile;

    private RuleBackupManager(){
        backupFile = new File(BACKUP_PATH);

        try {
            backupFile.createNewFile();
        } catch (IOException ex) {
            throw new RuntimeException("An I/O error occurred during the creation of the backup file!");
        }
    }

    public static RuleBackupManager getInstance(){
        return INSTANCE;
    }

    public void saveOnBinaryFile(ObservableList<Rule> list){
        List<Rule> newList = new ArrayList<>();
        newList.addAll(list);
        try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(backupFile)))){
            oos.writeObject(newList);
        }catch(FileNotFoundException ex){
            throw new NoFileFoundException("Error during the opening or creation of the specified backup file");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void loadFromBinaryFile(ObservableList<Rule> list){

        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(backupFile)))) {
            List<Rule> newList = (ArrayList<Rule>) ois.readObject();
            list.addAll(newList);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            throw new NoFileFoundException("Backup file not found");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
