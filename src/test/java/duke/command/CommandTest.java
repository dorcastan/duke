package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class CommandTest {
    private class StorageStub implements Storage {
        boolean isWorking;

        StorageStub(boolean isWorking) {
            this.isWorking = isWorking;
        }

        @Override
        public List<Task> load() {
            return new ArrayList<>();
        }

        @Override
        public void store(TaskList tasks) throws IOException {
            if (!this.isWorking) {
                throw new IOException("write error");
            }
        }
    }

    private TaskList tasks = new TaskList();

    @Test
    void save_workingStorage_success() {
        try {
            new ListCommand().save(tasks, new StorageStub(true));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void save_badStorage_exceptionThrown() {
        try {
            new ListCommand().save(tasks, new StorageStub(false));
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage()
                    .startsWith("oops! I encountered an error when saving your tasks.\n"));
        }
    }

    @Test
    void getTaskIndex_validInputString_returnZeroIndexedInt() {
        try {
            assertEquals(0, new ByeCommand().getTaskIndex("1", 3));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getTaskIndex_inputNotNumber_exceptionThrown() {
        try {
            new ByeCommand().getTaskIndex("abc", 3);
            fail();
        } catch (Exception e) {
            assertEquals("I couldn't find the task you requested!",
                    e.getMessage());
        }
    }

    @Test
    void getTaskIndex_inputNumberExceedsIndex_exceptionThrown() {
        try {
            new ByeCommand().getTaskIndex("23", 3);
            fail();
        } catch (Exception e) {
            assertEquals("I couldn't find the task you requested!",
                    e.getMessage());
        }
    }
}