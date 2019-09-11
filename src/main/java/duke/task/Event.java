package duke.task;

import duke.util.DateParser;

import java.util.Date;

/**
 * Events are Tasks that have a specific starting time.
 */
public class Event extends Task {

    /** Starting time of the event. */
    private Date time;

    /**
     * Creates a new Event with the given description and start time.
     *
     * @param description       Description of Event.
     * @param time              Starting time of the Event.
     */
    public Event(String description, String time) {
        super(description);
        this.time = DateParser.parse(time);
    }

    /**
     * Creates a new Event with the given description, start time and status.
     *
     * @param description       Description of Event.
     * @param time              Start time of the Event.
     * @param isDone            Whether the Event is done or not.
     */
    public Event(String description, String time, boolean isDone) {
        super(description, isDone);
        this.time = DateParser.parse(time);
    }

    /**
     * Returns the letter "E", representing the type Event.
     *
     * @return  The letter "E".
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Returns a representation of the current Event, including its time, in
     * an appropriate format for data storage.
     *
     * @return  String representing the current Event.
     */
    @Override
    public String formatAsData() {
        return super.formatAsData() + " | "
                + String.format("%1$ta, %1$td %1$tb %1$ty, %1$tR", time);
    }

    /**
     * Returns a string containing the type of Task, done status, description,
     * and time.
     *
     * @return  String describing the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + String.format("%1$ta, %1$td %1$tb %1$ty, %1$tR", time) + ")";
    }
}
