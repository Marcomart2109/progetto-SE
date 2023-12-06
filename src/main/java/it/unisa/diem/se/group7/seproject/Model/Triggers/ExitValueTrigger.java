package it.unisa.diem.se.group7.seproject.Model.Triggers;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ExitValueTrigger implements Trigger{

    private final File program;
    private final int expectedExitValue;
    private final long TIME_LIMIT = 5;
    private final TriggerType TYPE = TriggerType.EXIT_VALUE;

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
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }

        return expectedExitValue == actualExitValue;
    }

    @Override
    public TriggerType getTYPE() {
        return TYPE;
    }
}
