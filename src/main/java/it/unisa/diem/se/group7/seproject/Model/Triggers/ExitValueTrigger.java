package it.unisa.diem.se.group7.seproject.Model.Triggers;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * Represents a trigger based on the exit value of an external program.
 * <p>
 * The class implements the {@link Trigger} interface and is serializable.
 * It takes a {@link File} representing the program and an integer representing the expected exit value
 * as parameters in its constructor.
 * </p>
 * <p>
 * The exit value of the program is obtained by executing the program using {@link ProcessBuilder}
 * and waiting for it to finish with a max time limit of 5 seconds.
 * If the actual exit value matches the expected exit value, the evaluate method returns true, otherwise false.
 * </p>
 * <p>
 * The toString method provides a string representation of the trigger, indicating the program and the expected exit value.
 * </p>
 *
 * @see Trigger
 * @see Serializable
 */
public class ExitValueTrigger implements Trigger, Serializable {
    private final File program;
    private final int expectedExitValue;
    private final long TIME_LIMIT = 5;

    public ExitValueTrigger(File program, int expectedExitValue){
        this.program = program;
        this.program.setExecutable(true); //sets the access permission to allow execute operations
        this.expectedExitValue = expectedExitValue;
    }

    @Override
    public boolean evaluate() {
        ProcessBuilder pb = new ProcessBuilder(program.getPath()); //creation of the operating system process

        int actualExitValue;

        try {
            Process process = pb.start();
            process.waitFor(TIME_LIMIT, TimeUnit.SECONDS);
            actualExitValue = process.exitValue();
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }

        return expectedExitValue == actualExitValue;
    }


    @Override
    public String toString() {
        return "for the specified external program \"" + program + "\" the exit value is equal to " + expectedExitValue;
    }
}
