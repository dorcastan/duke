package duke.util;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import java.util.List;

import java.io.IOException;

/**
 * The Storage interface specifies the ability to load and store a list of
 * tasks from a storage facility.
 */
public interface Storage {

    /**
     * Loads tasks from external storage to a new list, then returns that list.
     *
     * @return List of tasks that were loaded from file.
     * @throws DukeException If tasks cannot be loaded from file.
     */
    List<Task> load() throws DukeException;

    /**
     * Writes the tasks in the given list into external storage.
     *
     * @param tasks List of tasks to be written.
     * @throws IOException If file cannot be found, etc.
     */
    void store(TaskList tasks) throws IOException;
}
