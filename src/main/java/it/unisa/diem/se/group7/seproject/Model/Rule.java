package it.unisa.diem.se.group7.seproject.Model;

public class Rule {

    String name;
    Action action;
    Trigger trigger;

    public Rule(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
