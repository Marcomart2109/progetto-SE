package it.unisa.diem.se.group7.seproject.Model.Actions;

/**
 * The NoFileFoundException class is a custom exception that is thrown
 * when a file is not found.
 */
public class NoFileFoundException extends RuntimeException{
    public NoFileFoundException(String s) {
        super(s);
    }
}
