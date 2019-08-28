package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TextUi;
import duke.task.TaskList;

public class ByeCommand extends Command {

    public ByeCommand() {
        super("");
        this.isExit = true;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws
            DukeException {
        save(tasks, storage);
        ui.showText("Bye. Hope to see you again soon!");
    }
}
