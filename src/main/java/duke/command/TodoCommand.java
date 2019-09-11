package duke.command;

import duke.task.TaskList;
import duke.task.Todo;
import duke.util.Storage;
import duke.util.TextUi;

/**
 * A TodoCommand contains instructions to create a new Todo task.
 */
public class TodoCommand extends AddCommand {

    /**
     * Constructs a new TodoCommand with the given command details.
     *
     * @param details Command details.
     */
    public TodoCommand(String details) {
        super(details);
    }

    /**
     * Creates a new Todo using the details specified in the current
     * TodoCommand, then adds it to the task list. The superclass method
     * execute(TaskList, TextUi, Storage) is called as part of the process.
     *
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Hard disk storage.
     * @return String containing Duke's response.
     */
    @Override
    public String execute(TaskList tasks, TextUi ui, Storage storage) {
        tasks.add(new Todo(details));
        return super.execute(tasks, ui, storage);
    }
}
