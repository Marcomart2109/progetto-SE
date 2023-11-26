package it.unisa.diem.se.group7.seproject.Model.Actions;

import java.io.*;
import javax.sound.sampled.*;

public class PlayAudioAction implements Action {
    private Clip clip;

    public PlayAudioAction(File audioFile){
        try {
            // Create an audio input stream from the audio file
            AudioInputStream ais = AudioSystem.getAudioInputStream(audioFile);
            // Create a Clip instance useful to load the audio data prior to playback, rather than being streamed in real time
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (LineUnavailableException exc) {
            throw new RuntimeException("Sorry. Cannot play audio files.");
        } catch (UnsupportedAudioFileException exc) {
            throw new UnsupportedFileFormatException("Unsupported file format for: " + audioFile);
        } catch (FileNotFoundException exc) {
            throw new NoFileFoundException("File not found: " + audioFile);
        } catch (IOException exc) {
            throw new RuntimeException("IOException: " + exc);
        }
    }

    @Override
    public void execute() {
        if (clip != null) {
            // Create a new thread for audio playback
            Thread audioThread = new Thread(){
                @Override
                public void run() {
                    clip.addLineListener(event -> {
                        if (event.getType() == LineEvent.Type.STOP) {
                            clip = null;
                        }
                    });
                    while (clip != null) {
                        clip.start();
                    }
                }
            };
            // Start the audio playback in a separate thread
            audioThread.start();
        }
    }

}

