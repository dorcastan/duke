package duke.command;

import duke.util.Storage;
import duke.util.TextUi;

import duke.task.Task;
import duke.task.TaskList;

/**
 * A FindCommand contains instructions to find all tasks that match a
 * specific pattern.
 */
public class FindCommand extends Command {

    /**
     * Creates a new FindCommand that can search for the given details.
     *
     * @param details String containing details that found tasks should contain.
     */
    public FindCommand(String details) {
        super(details);
    }

    /**
     * Displays all tasks that match the details contained in the current
     * FindCommand. Tasks are evaluated using their default toString() values
     * and are matched against the current command's details using the String
     * method contains(String).
     *
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Hard disk storage.
     * @return String containing Duke's response.
     */
    @Override
    public String execute(TaskList tasks, TextUi ui, Storage storage) {
        TaskList foundTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.toString().contains(this.details)) {
                foundTasks.add(task);
            }
        }
        return foundTasks.asIndexedString();
    }
}
