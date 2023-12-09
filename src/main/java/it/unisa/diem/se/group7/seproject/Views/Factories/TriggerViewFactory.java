package it.unisa.diem.se.group7.seproject.Views.Factories;

import it.unisa.diem.se.group7.seproject.Model.Triggers.TriggerType;
import it.unisa.diem.se.group7.seproject.Views.TriggerViews.*;

public class TriggerViewFactory {
    public static TriggerView createView(TriggerType triggerType) {
        switch (triggerType) {
            case TIME_TRIGGER:
                return new TimeTriggerView();
            case DAY_OF_WEEK:
                return new DayOfTheWeekTriggerView();
            case DAY_OF_MONTH:
                return new DayOfMonthTriggerView();
            case DAY_OF_YEAR:
                return new DayOfTheYearTriggerView();
            case EXIT_VALUE:
                return new ExitValueTriggerView();
            case FILE_EXISTS:
                return new FileExistsTriggerView();
            case FILE_SIZE:
                return new FileSizeTriggerView();
            default:
                throw new IllegalArgumentException("Unsupported trigger type: " + triggerType);
        }
    }
}
