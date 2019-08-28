package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TextUi;
import duke.task.Event;
import duke.task.TaskList;

public class EventCommand extends AddCommand {
    public EventCommand(String details) {
        super(details);
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws
            DukeException {
        String[] taskDetails = details.split(" /at ");
        tasks.add(new Event(taskDetails[0], taskDetails[1]));
        super.execute(tasks, ui, storage);
    }
}
