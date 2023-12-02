package it.unisa.diem.se.group7.seproject.Model.Triggers;

import javafx.fxml.LoadListener;
import javafx.scene.control.DatePicker;

import java.io.Serializable;
import java.time.LocalDate;

public class DayOfTheYearTrigger implements Trigger, Serializable {

    private final TriggerType TYPE = TriggerType.DAY_OF_YEAR;

    private LocalDate day;

    public DayOfTheYearTrigger() {
    }

    public DayOfTheYearTrigger(LocalDate day) {
        this.day = day;
    }

    @Override
    public boolean evaluate() {
        LocalDate currentDate = LocalDate.now();

        return currentDate.equals(day);
    }

    @Override
    public TriggerType getTYPE() {
        return null;
    }

    public void setSpecifiedDay(LocalDate specifiedDay) {
        this.day = specifiedDay;
    }
}
