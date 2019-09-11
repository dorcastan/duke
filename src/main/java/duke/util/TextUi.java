package duke.util;

import java.util.Scanner;

/**
 * This class handles all activities relating to the user interface, including
 * formatting or displaying input and output.
 */
public class TextUi {

    /* STATIC VARIABLES */

    /** Scanner for user input */
    private static final Scanner scanner = new Scanner(System.in);

    /** Duke logo for display */
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /** Horizontal line of width 60 characters */
    private static final String HORIZONTAL_LINE =
            "____________________________________________________________";

    /** Four space indentation */
    private static final String INDENT = "    ";

    /* METHODS TO READ INPUT */

    /**
     * Reads a command from standard input and returns it. If input is
     * terminated, automatically returns an exit command.
     *
     * @return String representing a user-given command.
     */
    public String readCommand() {
        return scanner.hasNext() ? scanner.nextLine() : "bye";
    }

    /* METHODS TO DISPLAY OUTPUT */

    /**
     * Prints the given text with indentation of five spaces.
     *
     * @param text Single line of text to be printed.
     */
    // TODO: Wrap text for longer lines
    private void show(String text) {
        System.out.println(INDENT + " " + text);
    }

    /**
     * Prints a horizontal line with indentation.
     */
    public void showLine() {
        System.out.println(INDENT + HORIZONTAL_LINE);
    }

    /**
     * Prints the given text with appropriate indentation.
     *
     * @param text Formatted text with appropriate line breaks. Maximum width
     *             of each line is 58 characters.
     */
    public void showText(String text) {
        String[] lines = text.split("\n");
        for (String line : lines) {
            show(line);
        }
    }

    /**
     * Prints an apology followed by the given message.
     *
     * @param message Message from the exception that caused the error.
     */
    public void showErrorMessage(String message) {
        showText("Sorry, " + message);
    }

    /**
     * Prints a welcome message consisting of the Duke logo and a greeting.
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
     * Prints a message indicating that tasks were not loaded from storage.
     */
    public void showLoadingError() {
        showLine();
        showText("Sorry, I could not retrieve your previous tasks.");
    }
}
