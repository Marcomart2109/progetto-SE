package it.unisa.diem.se.group7.seproject.Model.Rules;

import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RuleBackup {
    public static void saveOnBinaryFile(ObservableList<Rule> list){
        List<Rule> newList = new ArrayList<>();
        newList.addAll(list);
        try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("src/main/resources/saved.bin")))){
            oos.writeObject(newList);
        }catch(Exception ex){
            //throw new RuntimeException();
            ex.printStackTrace();
        }
    }

    public static void loadFromBinaryFile(ObservableList<Rule> list){

        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("src/main/resources/saved.bin")))) {
            List<Rule> newList = (ArrayList<Rule>) ois.readObject();
            list.addAll(newList);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
