package duke.util;

import duke.DukeException;
import duke.command.Command;
import duke.command.ByeCommand;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.PriorityCommand;
import duke.command.TodoCommand;
import duke.task.Priority;

/**
 * The Parser class is in charge of handling user-given commands. It ensures
 * that only valid inputs are processed and produces the relevant Command
 * objects.
 */
public class Parser {

    /**
     * Array of valid command types. Valid types are:
     * 1. done
     * 2. undone
     * 3. priority
     * 4. delete
     * 5. todo
     * 6. event
     * 7. deadline
     * 8. find
     * 9. list
     * 10. bye
     */
    private static final String[] VALID_COMMANDS = {
        "done", "undone", "priority", "delete", "todo", "event", "deadline",
        "find", "list", "bye"
    };

    /**
     * Parses the input string and returns a Command corresponding to the
     * required action.
     *
     * @param input String representation of the desired command.
     * @return Command corresponding to the input.
     * @throws DukeException If input is invalid, etc.
     */
    public static Command parse(String input) throws DukeException {
        String trimmedInput = input.strip();
        String commandType = trimmedInput.split(" ", 2)[0];
        String commandArgs = input.substring(commandType.length()).strip();
        validate(commandType, trimmedInput.length());
        return makeCommand(commandType, commandArgs);
    }

    /**
     * Parses the input string and returns a Priority corresponding to the
     * priority specified in the string.
     *
     * @param input String containing the name of priority level to be returned.
     * @return Priority level as specified in the input.
     * @throws DukeException If input is invalid.
     */
    public static Priority parsePriority(String input) throws DukeException {
        String trimmedInput = input.strip();
        switch(trimmedInput) {
        case "high":
            return Priority.HIGH;
        case "quite":
            // Fallthrough
        case "mid":
            // Fallthrough
        case "medium":
            return Priority.MEDIUM;
        case "low":
            return Priority.LOW;
        case "no":
            // Fallthrough
        case "none":
            return Priority.NONE;
        default:
            throw new DukeException("I don't recognise that priority level...");
        }
    }

    /**
     * Checks that a given command type is valid and that the input length
     * indicates a non-empty command description (or details).
     *
     * @param commandType Type of command to be checked.
     * @param inputLength Length of input.
     * @throws DukeException An exception with a message describing Duke's
     *                       response to the problem.
     */
    private static void validate(String commandType, int inputLength)
            throws DukeException {
        boolean isInvalidType = true;
        for (String type : VALID_COMMANDS) {
            if (commandType.equals(type)) {
                isInvalidType = false;
                break;
            }
        }
        if (isInvalidType) {
            throw new DukeException("I don't know what that means... :(");
        }

        boolean isCommandWithNoArgs = commandType.equals("list") || commandType.equals("bye");

        boolean isTooShort = !isCommandWithNoArgs && inputLength < commandType.length() + 2;
        if (isTooShort) {
            switch (commandType) {
            case "done":
                // Fallthrough
            case "undone":
                throw new DukeException("what's the task number again?");
            case "priority":
                throw new DukeException("which task are you assigning a priority to?");
            case "delete":
                throw new DukeException("I couldn't find a task to delete.");
            case "todo":
                throw new DukeException("I can't see the description of your todo.");
            case "event":
                throw new DukeException("I need to know the event description.");
            case "deadline":
                throw new DukeException("I didn't catch what you need to do.");
            case "find":
                throw new DukeException("I need something to find!");
            default: // should not reach here
                assert false;
            }
        }

        boolean hasExtraWords = isCommandWithNoArgs && inputLength > commandType.length();
        if (hasExtraWords) {
            throw new DukeException("did you mean to type another command?");
        }
        // TODO: Case insensitive commands
        // TODO: Validate format of "event" and "deadline" date/time
        // TODO: Use better control flow (not exceptions)
        // TODO: Add alternative commands e.g. "exit"
    }

    /**
     * Returns a Command based on the given type and arguments.
     *
     * @param type String of a valid command type.
     * @param commandArgs String of arguments to be included in the Command.
     * @return New Command of the given type with the given arguments.
     * @throws DukeException If an exception is thrown by a Command constructor
     *                       while creating a new Command, or the given command
     *                       type is invalid.
     */
    private static Command makeCommand(String type, String commandArgs) throws
            DukeException {
        // TODO: Make Parser parse the command arguments too
        switch (type) {
        case "done":
            return new DoneCommand(commandArgs, true);
        case "undone":
            return new DoneCommand(commandArgs, false);
        case "priority":
            return new PriorityCommand(commandArgs);
        case "delete":
            return new DeleteCommand(commandArgs);
        case "todo":
            return new TodoCommand(commandArgs);
        case "event":
            return new EventCommand(commandArgs);
        case "deadline":
            return new DeadlineCommand(commandArgs);
        case "find":
            return new FindCommand(commandArgs);
        case "list":
            return new ListCommand();
        case "bye":
            return new ByeCommand();
        default:
            // command type should have been checked before calling this method
            assert false;
            throw new DukeException("I don't know what that means... :(");
        }
    }
}
