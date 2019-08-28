package duke.command;

import duke.Storage;
import duke.TextUi;
import duke.task.TaskList;

public class ListCommand extends Command {

    public ListCommand() {
        super("");
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.showText("You have no tasks now. Hooray!");
        } else {
            ui.showList(tasks);
        }
    }
}
