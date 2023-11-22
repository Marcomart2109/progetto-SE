package it.unisa.diem.se.group7.seproject.Model;

public class ShowDialogBoxAction implements Action{
    private String message;


    public ShowDialogBoxAction(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void execute() {

    }
}
