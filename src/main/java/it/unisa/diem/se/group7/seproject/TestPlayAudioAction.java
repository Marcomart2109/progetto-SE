package it.unisa.diem.se.group7.seproject;

import it.unisa.diem.se.group7.seproject.Model.PlayAudioAction;
import java.io.File;

public class TestPlayAudioAction {

    public static void main(String[] args) {
        String path  = "C:\\Users\\Alessandro Pasquale\\Downloads\\file_example_WAV_1MG.wav";
        PlayAudioAction paa = new PlayAudioAction(new File(path));
        paa.execute();
    }

}
