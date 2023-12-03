package it.unisa.diem.se.group7.seproject.Model.Actions;

import java.io.*;
import javax.sound.sampled.*;

public class PlayAudioAction implements Action, Serializable {
    private transient Clip clip;
    private final File audioFile;
    private final ActionType TYPE = ActionType.PLAY_AUDIO;

    public PlayAudioAction(File audioFile){
        this.audioFile = audioFile;
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

    public PlayAudioAction(String filePath){
        this(new File(filePath));
    }

    @Override
    public ActionType getTYPE() {
        return TYPE;
    }

    @Override
    public void execute() {
        if (clip != null) {
            // Create a new thread for audio playback
            Thread audioThread = new Thread(() -> {
                clip.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.setFramePosition(0);
                    }
                });
                clip.start(); // Start audio playback
            });

            // Start the audio playback in a separate thread
            audioThread.start();
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {

        out.defaultWriteObject(); // write non-transient objects
        out.writeObject(audioFile.getPath());

    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {

        in.defaultReadObject(); // read non-transient objects

        // the following code is useful for restoring the clip object (which is not serializable)
        String filePath = (String) in.readObject();
        File audioFile = new File(filePath);

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(audioFile);
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
    public String toString() {
        return "THEN play an audio from \"" + audioFile + "\"";
    }
}

