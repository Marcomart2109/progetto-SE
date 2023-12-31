package it.unisa.diem.se.group7.seproject.Model.Actions;

import java.io.*;
import java.util.Objects;
import javax.sound.sampled.*;

/**
 * The PlayAudioAction class represents an action that plays an audio file.
 * It implements the Action interface.
 *
 * <p>
 * Sample usage:
 * <pre>
 * File audioFile = new File("audio.wav");
 * PlayAudioAction playAudioAction = new PlayAudioAction(audioFile);
 * playAudioAction.execute();
 * </pre>
 * </p>
 */
public class PlayAudioAction implements Action, Serializable {
    private transient Clip clip; // clip is not serializable
    private final File audioFile;

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

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {

        out.defaultWriteObject(); // write non-transient objects
        out.writeObject(audioFile.getPath());

    }

    @Serial
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayAudioAction that = (PlayAudioAction) o;
        return Objects.equals(audioFile, that.audioFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clip, audioFile);
    }

    @Override
    public String toString() {
        return "play an audio from \"" + audioFile + "\"";
    }

}

