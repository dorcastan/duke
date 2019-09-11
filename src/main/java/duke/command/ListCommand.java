package duke.command;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.TextUi;

/**
 * A ListCommand contains instructions to list all existing tasks.
 */
public class ListCommand extends Command {

    /**
     * Creates a new ListCommand, which requires no other details,
     */
    public ListCommand() {
        super("");
    }

    /**
     * Displays the current list of tasks on the given user interface, or
     * if the list is empty, displays an alternative message.
     *
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Hard disk storage.
     * @return String containing Duke's response.
     */
    @Override
    public String execute(TaskList tasks, TextUi ui, Storage storage) {
        String response;
        if (tasks.isEmpty()) {
            response = "You have no tasks now. Hooray!";
        } else {
            response = tasks.asIndexedString();
        }
        ui.showText(response);
        return response;
    }
}
