package duke.command;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.TextUi;

/**
 * The ByeCommand class is used when an exit command is given.
 */
public class ByeCommand extends Command {

    /**
     * Constructs a bye Command, which has the attribute isExit set to true.
     */
    public ByeCommand() {
        super(true);
    }

    /**
     * Displays an exit message on the given user interface.
     *
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Hard disk storage.
     * @return String containing Duke's response.
     */
    @Override
    public String execute(TaskList tasks, TextUi ui, Storage storage) {
        String byeMessage = "Bye. Hope to see you again soon!";
        ui.showText(byeMessage);
        return byeMessage;
    }
}
