class Parser {
    /**
     * Parses the input string and returns a Command corresponding to the
     * required action.
     * @param input             String representation of the desired command
     * @return                  Command
     * @throws DukeException    If input is invalid, etc.
     */
    static Command parse(String input) throws DukeException {
        validate(input);

        if (input.startsWith("done")) {
            return new DoneCommand(input.substring(5), true);
        } else if (input.startsWith("undo")) {
            return new DoneCommand(input.substring(5), false);
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(input.substring(7));
        } else if (input.startsWith("todo")) {
            return new TodoCommand(input.substring(5));
        } else if (input.startsWith("event")) {
            return new EventCommand(input.substring(6));
        } else if (input.startsWith("deadline")) {
            return new DeadlineCommand(input.substring(9));
        } else if (input.equals("list")) {
            return new ListCommand();
        } else { // input is "bye"
            return new ByeCommand();
        }
    }

    /**
     * Throws an exception if the given input does not have a valid format.
     * Valid formats are: 1. "list"
     *                    2. "done [taskIndex]"
     *                    3. "undo [taskIndex]"
     *                    4. "todo [description]"
     *                    5. "deadline [description] /by [time]"
     *                    6. "event [description] /at [time]"
     *                    7. "delete [taskIndex]"
     *                    8. "bye"
     * @param input             Text input to be validated
     * @throws DukeException    An exception with a message describing Duke's
     *                          response to the problem
     */
    private static void validate(String input) throws DukeException {
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
        } else if (!input.equals("list") && !input.equals("bye")) {
            throw new DukeException("I don't know what that means... :(");
        }
        // TODO: Case insensitive commands
        // TODO: Check format of "event" and "deadline" date/time
        // TODO: Use better control flow (not exceptions)
        // TODO: Add alternative commands e.g. "exit"
    }
}
