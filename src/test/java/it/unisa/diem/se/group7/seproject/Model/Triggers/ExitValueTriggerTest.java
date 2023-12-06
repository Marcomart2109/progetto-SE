package it.unisa.diem.se.group7.seproject.Model.Triggers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class ExitValueTriggerTest {
    private ExitValueTrigger evt;
    private File program;

    @Before
    public void setUp(){
        String osName = System.getProperty("os.name").toLowerCase();
        String commands;

        // the if-block is reserved to windows systems, the else-block to unix/linux systems
        if(osName.equals("windows")){
            program = new File("src/main/resources/testProgram.bat");
            commands = "@echo off" + System.lineSeparator() + "echo Hello world";
        }else{
            program = new File("src/main/resources/testProgram.sh");
            commands = "#!/bin/bash" + System.lineSeparator() + "echo \"Hello world\"";
        }

        //script creation in folder src/main/resources
        try{
            program.createNewFile();
        }catch(IOException exc){}

        //the following block is useful for writing the command lines in the empty script
        try {
            Files.write(program.toPath(), commands.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int expectedExitValue = 0;

        evt = new ExitValueTrigger(program, expectedExitValue);
    }

    @Test
    public void testEvaluate(){
        assertTrue(evt.evaluate(), "The actual exit value of the specified script differs from the expected one");
    }

    @After
    public void cleanUp(){
        program.delete();
    }

}