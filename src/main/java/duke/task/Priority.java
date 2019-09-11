package duke.task;

public enum Priority {
    HIGH("Important!!"),
    MEDIUM("Quite important"),
    LOW("Not very important"),
    NONE("None");

    private String descriptor;

    private Priority(String descriptor) {
        this.descriptor = descriptor;
    }

    /**
     * Returns a user-friendly String representation of a Priority.
     *
     * @return String containing a description of the current Priority.
     */
    @Override
    public String toString() {
        return this.descriptor;
    }
}
