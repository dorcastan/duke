package duke.command;

import duke.DukeException;
import duke.task.Event;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.TextUi;

/**
 * An EventCommand contains instructions to create an event task.
 */
public class EventCommand extends AddCommand {

    /**
     * Constructs a new EventCommand using the given details.
     *
     * @param details Command details.
     * @throws DukeException If the given details do not include an event time.
     */
    public EventCommand(String details) throws DukeException {
        super(details);
        if (!details.contains(" /at ")) {
            throw new DukeException("I need to know when your event is!");
        }
    }

    /**
     * Creates a new Event based on the details contained in the current
     * EventCommand, then adds the event to the task list. The superclass
     * method execute(TaskList, TextUi, Storage) is called as part of the
     * process.
     *
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Hard disk storage.
     * @return String containing Duke's response.
     */
    @Override
    public String execute(TaskList tasks, TextUi ui, Storage storage) {
        String[] taskDetails = details.split(" /at ", 2);
        tasks.add(new Event(taskDetails[0], taskDetails[1]));
        return super.execute(tasks, ui, storage);
    }
}
