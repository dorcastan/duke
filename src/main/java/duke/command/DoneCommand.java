package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TextUi;
import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand extends Command {

    private boolean isDone;

    public DoneCommand(String details, boolean isDone) {
        super(details);
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws
            DukeException {
        int taskIndex = getTaskIndex(details, tasks.size());
        Task selectedTask = tasks.get(taskIndex);
        if (isDone) {
            selectedTask.markAsDone();
            ui.showText("Nice! I've marked this task as done:"
                    + "\n  " + selectedTask.toString());
        } else {
            selectedTask.markAsUndone();
            ui.showText("Oh dear. I've marked this task as undone:"
                    + "\n  " + selectedTask.toString());
        }
        save(tasks, storage);
    }
}
