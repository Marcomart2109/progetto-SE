package it.unisa.diem.se.group7.seproject.Model.Rules;

import it.unisa.diem.se.group7.seproject.Model.Actions.NoFileFoundException;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RuleBackup {

    public static void saveOnBinaryFile(ObservableList<Rule> list, String backupPath){
        List<Rule> newList = new ArrayList<>();
        newList.addAll(list);
        try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(backupPath)))){
            oos.writeObject(newList);
        }catch(FileNotFoundException ex){
            throw new NoFileFoundException("Error during the opening or creation of the specified backup file");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void loadFromBinaryFile(ObservableList<Rule> list, String backupPath){

        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(backupPath)))) {
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
