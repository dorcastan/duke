package duke.command;

import duke.DukeException;
import duke.task.Priority;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TextUi;

public class PriorityCommand extends Command {
    /**
     * Creates a PriorityCommand with the given details, which should include
     * a task number and a Priority level.
     * @param details Details of this command.
     */
    public PriorityCommand(String details) {
        super(details);
    }

    /**
     * Finds the task specified by the current PriorityCommand's details, then
     * assigns it a priority level according to the priority specified in this
     * command's details.
     *
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Hard disk storage.
     * @return String containing Duke's response.
     * @throws DukeException If task index is invalid, list fails to be saved
     *                       to storage, etc.
     */
    @Override
    public String execute(TaskList tasks, TextUi ui, Storage storage)
            throws DukeException {

        // parse details
        String[] indivDetails = details.split(" +");
        int taskIndex = getTaskIndex(indivDetails[0], tasks.size());
        Task selectedTask = tasks.get(taskIndex);
        Priority newPriority = Parser.parsePriority(indivDetails[1]);

        // set priority level and show response
        selectedTask.setPriority(newPriority);
        String setPriorityMessage = "Okay! I've set this task's priority to "
                    + newPriority.toString() + ":\n"
                    + "  " + selectedTask.toString();
        ui.showText(setPriorityMessage);

        // try to save to storage
        try {
            save(tasks, storage);
        } catch (DukeException e) {
            System.err.print(e.getMessage());
        }
        return setPriorityMessage;
    }
}
