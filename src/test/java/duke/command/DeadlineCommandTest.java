package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.TextUi;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class DeadlineCommandTest {
    private class StorageStub implements Storage {

        @Override
        public List<Task> load() {
            return new ArrayList<>();
        }

        @Override
        public void store(TaskList tasks) {
            // do nothing
        }
    }

    @Test
    void execute_validCommand_taskAdded() {
        // set up
        TaskList tasks = new TaskList();
        TextUi ui = new TextUi();
        Storage storage = new StorageStub();

        // run tests
        try {
            new DeadlineCommand("work /by Thu").execute(tasks, ui, storage);
        } catch (Exception e) {
            fail();
        }

        // assertions
        assertEquals(1, tasks.size());
        assertEquals("[D][ ] work (by: Thu, 01 Jan 70, 00:00)",
                tasks.get(0).toString());
    }
}