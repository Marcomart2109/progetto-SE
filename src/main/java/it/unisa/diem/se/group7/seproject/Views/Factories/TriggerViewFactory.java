package it.unisa.diem.se.group7.seproject.Views.Factories;

import it.unisa.diem.se.group7.seproject.Model.Triggers.TriggerType;
import it.unisa.diem.se.group7.seproject.Views.TriggerViews.*;

/**
 * The TriggerViewFactory class is responsible for creating specific implementations of the TriggerView interface
 * based on the given TriggerType.
 *
 * <p> TriggerViewFactory provides a static method createView(TriggerType triggerType) that returns an instance
 * of a TriggerView implementation corresponding to the given TriggerType.
 *
 * <p> The available TriggerTypes and their corresponding TriggerView implementations are:
 * - TIME_TRIGGER: TimeTriggerView
 * - DAY_OF_WEEK: DayOfTheWeekTriggerView
 * - DAY_OF_MONTH: DayOfMonthTriggerView
 * - DAY_OF_YEAR: DayOfTheYearTriggerView
 * - EXIT_VALUE: ExitValueTriggerView
 * - FILE_EXISTS: FileExistsTriggerView
 * - FILE_SIZE: FileSizeTriggerView
 *
 * @see TriggerType
 */
public class TriggerViewFactory {
    public static TriggerView createView(TriggerType triggerType) {
        return switch (triggerType) {
            case TIME_TRIGGER -> new TimeTriggerView();
            case DAY_OF_WEEK -> new DayOfTheWeekTriggerView();
            case DAY_OF_MONTH -> new DayOfMonthTriggerView();
            case DAY_OF_YEAR -> new DayOfTheYearTriggerView();
            case EXIT_VALUE -> new ExitValueTriggerView();
            case FILE_EXISTS -> new FileExistsTriggerView();
            case FILE_SIZE -> new FileSizeTriggerView();
        };
    }
}
