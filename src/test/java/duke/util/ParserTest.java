package duke.util;

import duke.command.ByeCommand;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {
    @Test
    public void parse_invalidInput_exceptionThrown() {

        // empty string
        try {
            assertNull(Parser.parse(""));
            fail();
        } catch (Exception e) {
            assertEquals("I don't know what that means... :(", e.getMessage());
        }

        // random string
        try {
            assertNull(Parser.parse("test"));
            fail();
        } catch (Exception e) {
            assertEquals("I don't know what that means... :(", e.getMessage());
        }
    }

    @Test
    public void parse_validCommands_success() throws Exception {

        // commands with no details
        assertEquals(new ListCommand().getDetails(),
                Parser.parse("list").getDetails());
        assertEquals(new ByeCommand().getDetails(),
                Parser.parse("bye").getDetails());

        // done and undo commands
        assertTrue(Parser.parse("done 4") instanceof DoneCommand);
        assertTrue(Parser.parse("undo 4") instanceof DoneCommand);
        assertTrue(Parser.parse("done 4").isDone());
        assertFalse(Parser.parse("undo 4").isDone());

        // commands with task index
        assertEquals(new DoneCommand("4", true).getDetails(),
                Parser.parse("done 4").getDetails());
        assertEquals(new DeleteCommand("4").getDetails(),
                Parser.parse("delete 4").getDetails());

        // commands to add tasks
        assertEquals(new DeadlineCommand("work /by now").getDetails(),
                Parser.parse("deadline work /by now").getDetails());
        assertEquals(new EventCommand("meeting /at 3PM").getDetails(),
                Parser.parse("event meeting /at 3PM").getDetails());
        assertEquals(new TodoCommand("work").getDetails(),
                Parser.parse("todo work").getDetails());
    }

    @Test
    public void parse_leadingAndTrailingWhitespace_success() throws Exception {
        assertEquals(new ListCommand().getDetails(),
                Parser.parse("   list   ").getDetails());
        assertEquals(new TodoCommand("work").getDetails(),
                Parser.parse("  todo work  ").getDetails());
    }
}