package it.unisa.diem.se.group7.seproject.Model;

public class Rule {

    String name;
    Action action;
    Trigger trigger;

    public Rule(String name) {
        this.name = name;
    }

    public Rule(Trigger trigger, Action action) {
        this.action = action;
        this.trigger = trigger;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
