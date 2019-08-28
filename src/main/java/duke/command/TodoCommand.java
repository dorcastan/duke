package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TextUi;
import duke.task.TaskList;
import duke.task.Todo;

public class TodoCommand extends AddCommand {
    public TodoCommand(String details) {
        super(details);
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws
            DukeException {
        tasks.add(new Todo(details));
        super.execute(tasks, ui, storage);
    }
}
