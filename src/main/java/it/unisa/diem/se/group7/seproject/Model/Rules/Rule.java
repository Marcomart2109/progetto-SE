package it.unisa.diem.se.group7.seproject.Model.Rules;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;

import java.io.Serializable;

public interface Rule {



    public boolean evaluate() ;
    public void execute() ;
   public String getName();
    public Action getAction();
    public Trigger getTrigger() ;

}
