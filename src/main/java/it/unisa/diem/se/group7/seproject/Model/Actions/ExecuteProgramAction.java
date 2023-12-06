package it.unisa.diem.se.group7.seproject.Model.Actions;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExecuteProgramAction implements Action, Serializable {
    private File externalProgram;
    private List<String> arguments;
    private final ActionType TYPE = ActionType.EXECUTE_PROGRAM;

    public ExecuteProgramAction(File externalProgram, String commandLineArguments) {
        this.externalProgram = externalProgram;
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
            int exitCode = process.waitFor();

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
