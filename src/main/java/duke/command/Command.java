package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TextUi;
import duke.task.TaskList;

import java.io.IOException;

/**
 * The Command class is used to pass commands around the Duke application
 * once user input has been parsed.
 */
public abstract class Command {
    /** Details of the command. */
    protected String details;

    /** Whether the command is an exit command. */
    protected boolean isExit;

    /** Whether the command marks a task as done. */
    protected boolean isDone;

    /**
     * Constructs a Command with the given details. By default, the boolean
     * values isExit and isDone are both set to false.
     *
     * @param details   Details of the command.
     */
    public Command(String details) {
        this.details = details;
        this.isExit = false;
        this.isDone = false;
    }

    /**
     * Returns the details associated with this command.
     *
     * @return  Command details.
     */
    public String getDetails() {
        return details;
    }

    /**
     * Checks whether this is an exit command or not.
     *
     * @return  True if this is an exit command and false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Checks whether this command is a done command (marks a task as done)
     * or not. Not to be confused with whether this command is an instance of
     * the DoneCommand class.
     *
     * @return  True if executing this command will result in a task being
     *          marked as done, and false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Executes the current command using the given resources.
     *
     * @param tasks             List of tasks.
     * @param ui                User interface.
     * @param storage           Hard disk storage.
     * @return                  String containing Duke's response to the command.
     * @throws DukeException    If command details are invalid, etc.
     */
    public abstract String execute(TaskList tasks, TextUi ui, Storage storage) throws
            DukeException;

    /**
     * Saves the current task list using the given storage.
     *
     * @param tasks             List of tasks to be saved.
     * @param storage           Storage to save task list in.
     * @throws DukeException    If file does not get written properly.
     */
    protected void save(TaskList tasks, Storage storage) throws DukeException {
        try {
            storage.store(tasks);
        } catch (IOException e) {
            throw new DukeException(
                    "Oops! I encountered an error when saving your tasks.\n"
                            + "    " + e.getMessage() + "\n"
                            + "If you say bye now, you may not be able to access this\n"
                            + " list in future.");
        }
    }

    /**
     * Returns the index of the taskList task with the given number if such a
     * task exists, and throws an exception otherwise. Note that the input
     * number is one-indexed, whereas taskList is zero-indexed.
     *
     * @param number            String that should contain a number.
     * @param numberOfTasks     Number of tasks in the list currently.
     * @return                  The requested task index.
     * @throws DukeException    Exception message indicating task not found.
     */
    public int getTaskIndex(String number, int numberOfTasks) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(number);
            if (taskIndex < 1 || taskIndex > numberOfTasks) {
                throw new DukeException("I couldn't find the task you requested!");
            }
            return taskIndex - 1; // taskList is zero-indexed
        } catch (NumberFormatException e) {
            throw new DukeException("I couldn't find the task you requested!");
        }
    }
}
