package it.unisa.diem.se.group7.seproject.Model.Triggers;

import javafx.fxml.LoadListener;
import javafx.scene.control.DatePicker;

import java.io.Serializable;
import java.time.LocalDate;

public class DayOfTheYearTrigger implements Trigger, Serializable {


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


    public void setSpecifiedDay(LocalDate specifiedDay) {
        this.day = specifiedDay;
    }

    @Override
    public String toString() {
        return "the current day is the " + day;
    }
}
