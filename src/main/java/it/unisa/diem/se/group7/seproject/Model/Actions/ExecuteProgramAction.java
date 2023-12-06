package it.unisa.diem.se.group7.seproject.Model.Actions;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ExecuteProgramAction implements Action, Serializable {
    private File externalProgram;
    private List<String> arguments;
    private final ActionType TYPE = ActionType.EXECUTE_PROGRAM;

    private final int TIMEOUT_LIMIT = 5;

    public ExecuteProgramAction(File externalProgram, String commandLineArguments) {
        this.externalProgram = externalProgram;
        externalProgram.setExecutable(true); //Set executable permissions for the file
        // Arguments initialization in the constructor
        arguments = Arrays.asList(commandLineArguments.split("\s+"));
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


    public File getExternalProgram() {
        return externalProgram;
    }

    public List<String> getArguments() {
        return arguments;
    }

    @Override
    public ActionType getTYPE() {
        return TYPE;
    }
}
