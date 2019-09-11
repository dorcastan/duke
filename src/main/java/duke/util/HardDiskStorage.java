package duke.util;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * An implementation of Storage that stores and loads data on the hard disk.
 */
public class HardDiskStorage implements Storage {

    /** File to read and write from. */
    private static File dataFile;

    /**
     * Creates a Storage object that can read and write from a hard disk file.
     *
     * @param filePath Relative path to data file.
     */
    public HardDiskStorage(String filePath) {
        dataFile = new File(filePath);
    }

    /**
     * Loads tasks from a data file and returns a list containing the tasks.
     * If file is not found or cannot be read, a DukeException is thrown.
     *
     * @return List of tasks from data file.
     * @throws DukeException If file does not exist, tasks cannot be loaded, etc.
     */
    @Override
    public List<Task> load() throws DukeException {
        List<Task> taskList = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(dataFile);
            while (fileScanner.hasNext()) {
                String task = fileScanner.nextLine();
                String[] details = task.split(" \\| ");
                boolean isDone = Task.checkStatus(details[1]);
                switch(details[0]) {
                case "T":
                    taskList.add(new Todo(details[2], isDone));
                case "E":
                    taskList.add(new Event(details[2], details[3], isDone));
                case "D":
                    taskList.add(new Deadline(details[2], details[3], isDone));
                default:
                    taskList.add(new Task("This task could not be parsed "
                            + "from the given data file."));
                    // TODO: Find better way to handle parsing error.
                }
            }
            return taskList;
        } catch (FileNotFoundException e) {
            throw new DukeException("No such file was found.");
        }
    }

    /**
     * Stores the tasks in the given list to the hard disk file. If necessary,
     * a new file and its parent directories are created before storing the
     * task list.
     *
     * @param tasks List of tasks to be written.
     * @throws IOException If file cannot be created, file cannot be written,
     *                     etc.
     */
    @Override
    public void store(TaskList tasks) throws IOException {
        if (!dataFile.exists()) {
            dataFile.getParentFile().mkdirs();
            dataFile.createNewFile();
        }
        assert dataFile.exists();
        FileWriter fileWriter = new FileWriter(dataFile);
        fileWriter.write(tasks.toString());
        fileWriter.close();
    }
}