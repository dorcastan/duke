package duke.command;

import duke.DukeException;
import duke.task.TaskList;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TextUi;

/**
 * A DeleteCommand communicates instructions for a task to be deleted.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a new DeleteCommand with the given details.
     *
     * @param details Details of task to be deleted.
     */
    public DeleteCommand(String details) {
        super(details);
    }

    /**
     * Removes the task specified by the current DeleteCommand's details from
     * the given task list, then saves the new list to storage.
     *
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Hard disk storage.
     * @throws DukeException If storage fails, etc.
     * @return String containing Duke's response.
     */
    @Override
    public String execute(TaskList tasks, TextUi ui, Storage storage) throws
            DukeException {
        int taskIndex = getTaskIndex(details, tasks.size());
        Task deletedTask = tasks.remove(taskIndex);
        String textToDisplay = "Noted. I've removed this task:"
                + "\n  " + deletedTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.\n";
        ui.showText(textToDisplay);
        try {
            save(tasks, storage);
        } catch (DukeException e) {
            System.err.print(e.getMessage());
        }
        return textToDisplay;
    }
}
