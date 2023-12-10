package it.unisa.diem.se.group7.seproject.Model.Triggers;

/**
 * The TriggerType enum represents the types of triggers that can be used in an application.
 * These triggers determine when certain actions or events should occur.
 *
 * <p> The available trigger types are:
 * - TIME_TRIGGER: A trigger based on a specific time of day.
 * - DAY_OF_WEEK: A trigger based on a specific day of the week.
 * - DAY_OF_MONTH: A trigger based on a specific day of the month.
 * - DAY_OF_YEAR: A trigger based on a specific day of the year.
 * - EXIT_VALUE: A trigger based on the exit value of a process.
 * - FILE_EXISTS: A trigger based on the existence of a file.
 * - FILE_SIZE: A trigger based on the size of a file.
 *
 */
public enum TriggerType {
    TIME_TRIGGER,
    DAY_OF_WEEK,
    DAY_OF_MONTH,
    DAY_OF_YEAR,
    EXIT_VALUE,
    FILE_EXISTS,
    FILE_SIZE,
}
