package it.unisa.diem.se.group7.seproject.Model.Actions;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * This class represents an executable, parameterized action, which is to run an external program.
 * <p>
 * It implements the Action interface and java.io.Serializable interface.
 * <p>
 * The class is initialized with the external program (as a Java File object) and a series of command line arguments.
 * The file is set to be executable, and the command line arguments are split into an List<String>.
 * <p>
 * This class also comes with a TIMEOUT_LIMIT constant which denotes the time limit before the process is forcefully closed.
 * <p>
 * Example usage:
 * File program = new File("/path/to/your/program");
 * ExecuteProgramAction action = new ExecuteProgramAction(program, "-a argument -b another_argument");
 * action.execute();
 * ```
 */

public class ExecuteProgramAction implements Action, Serializable {
    private final File externalProgram;
    private final List<String> arguments;

    private final int TIMEOUT_LIMIT = 5;

    public ExecuteProgramAction(File externalProgram, String commandLineArguments) {
        this.externalProgram = externalProgram;
        externalProgram.setExecutable(true); //Set executable permissions for the file
        // Arguments initialization in the constructor
        arguments = Arrays.asList(commandLineArguments.split(" +"));
    }

    @Override
    public void execute() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        List<String> command = new ArrayList<>();
        command.add(externalProgram.getAbsolutePath());
        command.addAll(arguments);
        processBuilder.command(command);

        try {
            Process process = processBuilder.start();
            int exitCode;

            // Wait for the process to complete with a timeout
            if (process.waitFor(TIMEOUT_LIMIT, TimeUnit.SECONDS)) {
                exitCode = process.exitValue();
            } else {
                // Process did not complete within the timeout
                process.destroy(); // Terminate the process
                throw new RuntimeException("External program execution timed out");
            }

            // Print the exit code of the external program ran
            System.out.println("External program exited with code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error in the execution of the external program");
        }
    }

    public List<String> getArguments() {
        return arguments;
    }


    @Override
    public String toString() {
        return "execute the program: \"" + externalProgram + "\" with these command line arguments: \"" + arguments + "\"";
    }
}
