package duke.task;

public class Todo extends Task {
    /**
     * Creates a new Todo task with the given description.
     * @param description       Task to be completed.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a new Todo task with the given description and status.
     * @param description       Task to be completed.
     * @param isDone            Whether the Task has been completed or not.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the letter "T", representing the type Todo.
     * @return  "T"
     */
    @Override
    String getType() {
        return "T";
    }

    /**
     * Returns a string containing the type of Task, done status, and
     * description.
     * @return  String describing the Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
