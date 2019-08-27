import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class DukeLogic {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final List<Task> taskList = new ArrayList<>();

    /**
     * Throws an exception if the given input does not have a valid format.
     * Valid formats are: 1. "list"
     *                    2. "done [taskIndex]"
     *                    3. "undo [taskIndex]"
     *                    4. "todo [description]"
     *                    5. "deadline [description] /by [time]"
     *                    6. "event [description] /at [time]"
     *                    7. "delete [taskIndex]"
     * @param input             Text input to be validated
     * @throws DukeException    An exception with a message describing Duke's
     *                          response to the problem
     */
    private void validate(String input) throws DukeException {
        if (input.startsWith("done") || input.startsWith("undo")) {
            if (input.length() < 6) {
                throw new DukeException("I couldn't find a task to look up.");
            }
        } else if (input.startsWith("delete")) {
            if (input.length() < 8) {
                throw new DukeException("I couldn't find a task to delete.");
            }
        } else if (input.startsWith("todo")) {
            if (input.length() < 6) {
                throw new DukeException("I can't see the description of your todo.");
            }
        } else if (input.startsWith("event")) {
            if (input.length() < 7) {
                throw new DukeException("I need to know the event description.");
            } else if (!input.contains(" /at ")) {
                throw new DukeException("I also need to know when your event is.");
            }
        } else if (input.startsWith("deadline")) {
            if (input.length() < 10) {
                throw new DukeException("I didn't catch what you need to do.");
            } else if (!input.contains(" /by ")) {
                throw new DukeException("what's the deadline for this?");
            }
        } else if (!input.equals("list")){
            throw new DukeException("I don't know what that means... :(");
        }
    }

    /**
     * If the input is "list", prints the full list of tasks. If the input is
     * "done" or "undo" followed by an integer, marks the task corresponding to
     * the index integer as done or undone respectively. Otherwise, adds the
     * given input to the list of tasks.
     * @param input     User text to be processed.
     */
    private void process(String input) throws DukeException {
        if (input.equalsIgnoreCase("list")) {
            if (taskList.isEmpty()) {
                DukeFormatter.prettyPrint("You have no tasks now. Hooray!");
            } else {
                DukeFormatter.prettyPrint(taskList);
            }
        } else if (input.startsWith("done")) {
            int taskIndex = getTaskIndex(input.substring(5));
            Task selectedTask = taskList.get(taskIndex);
            selectedTask.markAsDone();
            DukeFormatter.prettyPrint("Nice! I've marked this task as done:"
                    + "\n  " + selectedTask.toString());
            save();
        } else if (input.startsWith("undo")) {
            int taskIndex = getTaskIndex(input.substring(5));
            Task selectedTask = taskList.get(taskIndex);
            selectedTask.markAsUndone();
            DukeFormatter.prettyPrint("Oh dear. I've marked this task as undone:"
                    + "\n  " + selectedTask.toString());
            save();
        } else if (input.startsWith("delete")) {
            int taskIndex = getTaskIndex(input.substring(7));
            Task deletedTask = taskList.remove(taskIndex);
            DukeFormatter.prettyPrint("Noted. I've removed this task:"
                    + "\n  " + deletedTask.toString()
                    + "\nNow you have " + taskList.size() + " tasks in the list.");
            save();
        } else {
            addNewTask(input);
            int numberOfTasks = taskList.size();
            DukeFormatter.prettyPrint("Got it. I've added this task:"
                    + "\n  " + taskList.get(numberOfTasks - 1)
                    + "\nNow you have " + numberOfTasks + " tasks in the list.");
            save();
        }
    }

    /**
     * Saves the current task list in the file `[root]/data/duke.txt`.
     */
    private void save() {
        try {
            DukeFileHandler.writeToFile(taskList);
        } catch (IOException e) {
            DukeFormatter.prettyPrint(
                    "Oops! I encountered an error when saving your tasks.\n"
                            + "    " + e.getMessage() + "\n"
                            + "If you say bye now, you may not be able to access this\n"
                            + " list in future.");
        }
    }

    /**
     * Returns the taskList index of the task with the given number if such a
     * task exists, and throws an exception otherwise. Note that taskList is
     * zero-indexed, whereas the input number is one-indexed.
     * @param number            String containing a (possibly invalid) number
     * @return                  The requested task index
     * @throws DukeException    Exception message indicating task not found
     */
    private int getTaskIndex(String number) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(number);
            if (!isValid(taskIndex)) {
                throw new DukeException("I couldn't find the task you requested!");
            }
            return taskIndex - 1; // taskList is zero-indexed
        } catch (NumberFormatException e) {
            throw new DukeException("I couldn't find the task you requested!");
        }
    }

    /**
     * Returns true if a task corresponding to the given index exists and
     * false otherwise.
     * @param taskIndex     Task index to be validated
     * @return              True if the index is valid and false otherwise
     */
    private boolean isValid(int taskIndex) {
        return taskIndex >= 1 && taskIndex <= taskList.size();
    }

    /**
     * Add a new task to the list according to the given task type - Todo,
     * Event, or Deadline.
     * @param task      Description of task and other relevant details.
     */
    private void addNewTask(String task) {
        if (task.startsWith("todo")) {
            taskList.add(new Todo(task.substring(5)));
        } else if (task.startsWith("event")) {
            String[] taskDetails = task.substring(6).split(" /at ");
            taskList.add(new Event(taskDetails[0], taskDetails[1]));
        } else if (task.startsWith("deadline")) {
            String[] taskDetails = task.substring(9).split(" /by ");
            taskList.add(new Deadline(taskDetails[0], taskDetails[1]));
        } else {
            taskList.add(new Todo(task));
        }
    }

    /**
     * Runs the main application by handling user input.
     *
     * Duke begins by printing a welcome message. Subsequently, it repeatedly
     * scans for user input, then validates and processes it accordingly. The
     * function returns when the command to exit ("bye") is received.
     */
     void runApplication() {
        DukeFormatter.printWelcomeMessage();
        try {
            DukeFileHandler.readTasksFromFile(taskList);
        } catch (DukeException e) {
            DukeFormatter.printErrorMessage(e);
        }
        while (SCANNER.hasNext()) {
            String userInput = SCANNER.nextLine();
            userInput = userInput.strip();
            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }
            // TODO: add alternative commands e.g. "exit"
            try {
                validate(userInput);
                process(userInput);
            } catch (DukeException e) {
                DukeFormatter.printErrorMessage(e);
                // TODO: Add "help" feature: list all supported commands
            }
        }
        DukeFormatter.prettyPrint("Bye. Hope to see you again soon!");
    }
}
