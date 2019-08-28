package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TextUi;
import duke.task.TaskList;
import duke.task.Task;

public class DeleteCommand extends Command {

    public DeleteCommand(String details) {
        super(details);
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws
            DukeException {
        int taskIndex = getTaskIndex(details, tasks.size());
        Task deletedTask = tasks.remove(taskIndex);
        ui.showText("Noted. I've removed this task:"
                + "\n  " + deletedTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.");
        save(tasks, storage);
    }
}
