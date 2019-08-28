package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    /**
     * Array of date formats that Duke is able to parse. A valid date can take
     * any of the following formats:
     * 1. EEE, dd MMM yy, hh:mm
     * 2. dd-MM-yyyy hh:mm
     * 3. dd-MM-yyyy
     * 4. dd-MM-yy
     * 5. hh:mm
     * 6. EEE
     * where EEE is the day of week (e.g. Mon, Fri).
     */
    public static final SimpleDateFormat[] DATE_FORMATS = {
            new SimpleDateFormat("EEE, dd MMM yy, hh:mm"),
            new SimpleDateFormat("dd-MM-yy hh:mm"),
            new SimpleDateFormat("dd-MM-yyyy"),
            new SimpleDateFormat("dd-MM-yy"),
            new SimpleDateFormat("hh:mm"),
            new SimpleDateFormat("EEE")
    };

    private String description;
    private boolean isDone;

    /**
     * Creates a new undone Task with the given description.
     * @param description   Description of the Task. Description length should
     *                      be at most 50 characters (for now).
     */
    public Task(String description) {
        this(description, false);
    }

    /**
     * Creates a new Task with the given description and isDone status.
     * @param description   Description of the Task. Description length should
     *                      be at most 50 characters (for now).
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a Date corresponding to the date represented by the given String.
     * If the input format is invalid (does not match any of the date formats
     * specified in Task.DATE_FORMATS), returns the Date representing the
     * current instant.
     * @param date      String representing a date
     * @return Date     Date corresponding to the given date String, or the
     *                  current instant
     */
    static Date parseDate(String date) {
        for (SimpleDateFormat format : DATE_FORMATS) {
            try {
                return format.parse(date);
            } catch (ParseException e) {
                // do nothing and try the next format
            }
        }
        return new Date();
    }

    /**
     * Returns a tick or X symbol according to the isDone status of the
     * current task.
     * @return  A tick symbol if the Task is done, and an X symbol otherwise.
     */
    private String getStatusIcon() {
        return isDone ? "+" : " ";
        // return isDone ? "\u2713" : "\u2718"; // return tick or X symbols
    }

    /**
     * Returns a String of length 1 that indicates the current Task type.
     * @return  String indicating Task type
     */
    String getType() {
        return "-";
    }

    /**
     * Indicates that the current Task has been completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Indicates that the current Task has not been completed.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a representation of the current Task in an appropriate
     * format for data storage.
     * @return  String representing the current Task.
     */
    public String formatAsData() {
        return getType() + " | " + getStatusIcon() + " | " + description;
    }

    /**
     * Returns the description of the Task along with an indication of its
     * isDone status.
     * @return  String containing the status and description of the current
     *          Task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
