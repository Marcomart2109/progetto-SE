package it.unisa.diem.se.group7.seproject.Model.Rules;

import it.unisa.diem.se.group7.seproject.Model.Actions.Action;
import it.unisa.diem.se.group7.seproject.Model.Triggers.Trigger;

public class RuleDecorator implements Rule{
    private Rule rule;

    @Override
    public boolean evaluate() {
        return rule.getTrigger().evaluate();
    }

    @Override
    public void execute() {
        if(!isFired()){
            rule.getAction().execute();
            System.out.println("Rule \""+ this.rule.getName() + "\"is triggered");
            setFired(true);
        }
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Action getAction() {
        return null;
    }

    @Override
    public Trigger getTrigger() {
        return null;
    }

    private void setFired(boolean b) {

    }

    private boolean isFired() {
        return false;
    }


}
