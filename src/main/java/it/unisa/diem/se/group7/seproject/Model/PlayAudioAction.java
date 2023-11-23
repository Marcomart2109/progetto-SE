package it.unisa.diem.se.group7.seproject.Model;

import java.io.*;
import javax.sound.sampled.*;

public class PlayAudioAction implements Action{
    private Clip clip;

    public PlayAudioAction(File audioFile){
        if(audioFile.isFile()){
            try {
                // Create an audio input stream from the audio file
                AudioInputStream ais = AudioSystem.getAudioInputStream(audioFile);
                // Create a Clip instance useful to load the audio data prior to playback, rather than being streamed in real time
                this.clip = AudioSystem.getClip();
                this.clip.open(ais);
            } catch (LineUnavailableException exc) {
                throw new RuntimeException("Sorry. Cannot play audio files.");
            } catch (UnsupportedAudioFileException exc) {
                throw new RuntimeException("Unsupported file format for: " + audioFile);
            } catch (IOException exc) {
                throw new RuntimeException("IOException: " + exc);
            }
        }
    }

    // the action run when the corresponding rule is activated
    @Override
    public void execute() {
        if (clip != null) {
            clip.start();
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });
        }
    }

}

