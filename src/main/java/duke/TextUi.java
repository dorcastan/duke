package duke;

import duke.task.TaskList;

import java.util.Scanner;

/**
 * This class handles the user interface – all activities relating to input and
 * output, including format or printing.
 */
public class TextUi {

    /* STATIC VARIABLES */

    private static final Scanner scanner = new Scanner(System.in);

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String HORIZONTAL_LINE =
            "____________________________________________________________";

    private static final String INDENT = "    ";

    /* METHODS TO READ INPUT */

    /**
     * Reads a command from standard input. If user input is terminated,
     * automatically returns an exit command.
     * @return  String representing a user-given command
     */
    public String readCommand() {
        return scanner.hasNext() ? scanner.nextLine() : "bye";
    }

    /* METHODS TO DISPLAY OUTPUT */

    /**
     * Prints the given text with indentation of five spaces.
     * @param text      Single line of text to be printed
     */
    // TODO: Wrap text for longer lines
    private void show(String text) {
        System.out.println(INDENT + " " + text);
    }

    /**
     * Prints a horizontal line of width 60 characters.
     */
    public void showLine() {
        System.out.println(INDENT + HORIZONTAL_LINE);
    }

    /**
     * Prints the items in the given list with their corresponding index
     * numbers. A horizontal line is printed above and below the list, and
     * output is indented throughout.
     * @param list      A task list to be printed
     */
    public void showList(TaskList list) {
        for (int i = 1; i <= list.size(); i++) {
            show(i + "." + list.get(i - 1).toString());
        }
    }

    /**
     * Prints the given text with a horizontal line above and below the text
     * and appropriate indentation.
     * @param text      Formatted text with appropriate line breaks. Maximum
     *                  width for each line is 58 characters.
     */
    public void showText(String text) {
        String[] lines = text.split("\n");
        for (String line : lines) {
            show(line);
        }
    }

    /**
     * Prints an apology followed by details of the given exception.
     * @param message   Error message from exception that caused the error
     */
    public void showErrorMessage(String message) {
        showText("Sorry, " + message);
    }

    /**
     * Prints a welcome message that consists of the Duke logo and a greeting.
     */
    public void showWelcomeMessage() {
        showLine();
        showText(LOGO
                + "\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?");
        showLine();
    }

    /**
     * Prints a message indicating that tasks could not be loaded from storage.
     */
    public void showLoadingError() {
        showLine();
        showText("Sorry, I could not retrieve your previous tasks.");
        showLine();
    }
}
