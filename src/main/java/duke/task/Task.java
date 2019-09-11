package duke.task;

/**
 * Tasks are the main data of the Duke application, describing something that
 * needs to be done.
 */
public class Task {
    /**
     * Checks whether the given String is the status icon for a done Task.
     *
     * @param icon A string containing a valid done or undone icon.
     * @return true if the icon represents a "done" status, and false otherwise.
     */
    public static boolean checkStatus(String icon) {
        assert icon.equals("+") || icon.equals(" "); // assume no change to icons
        String doneIcon = new Task().getStatusIcon();
        return icon.equals(doneIcon);
    }

    /** Description of task */
    private String description;

    /** Whether the task has been completed */
    private boolean isDone;

    /**
     * Creates a new done Task with an empty description, for the convenient
     * checking of status icons.
     */
    private Task() {
        this("", true);
    }

    /**
     * Creates a new undone Task with the given description.
     *
     * @param description Description of the Task. Description length should
     *                    be at most 50 characters (for now).
     */
    public Task(String description) {
        this(description, false);
    }

    /**
     * Creates a new Task with the given description and isDone status.
     *
     * @param description Description of the Task. Description length should
     *                    be at most 50 characters (for now).
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a plus symbol or space according to the isDone status of the
     * current task.
     *
     * @return The status icon associated with the current task.
     */
    private String getStatusIcon() {
        return isDone ? "+" : " ";
        // return isDone ? "\u2713" : "\u2718"; // return tick or X symbols
    }

    /**
     * Returns a String of length 1 that indicates the current Task type.
     *
     * @return String indicating Task type.
     */
    public String getType() {
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
     *
     * @return String representing the current Task.
     */
    public String formatAsData() {
        return getType() + " | " + getStatusIcon() + " | " + description;
    }

    /**
     * Returns the description of the Task along with an indication of its
     * isDone status.
     *
     * @return String containing the status and description of the current
     *         Task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
