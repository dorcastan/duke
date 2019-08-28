package duke;

import duke.task.*;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

public class Storage {
    private static File dataFile;

    public Storage(String filePath) {
        dataFile = new File(filePath);
    }

    /**
     * Loads tasks from a (valid) data file, adds them to a new list, then
     * returns that list.
     * @throws DukeException    If tasks cannot be loaded from file
     * @return                  List of tasks that were loaded from file
     */
    public List<Task> load() throws DukeException {
        List<Task> taskList = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(dataFile);
            while (fileScanner.hasNext()) {
                String task = fileScanner.nextLine();
                String[] details = task.split(" \\| ");
                boolean isDone = details[1].equals("+");
                if (details[0].equals("T")) {
                    taskList.add(new Todo(details[2], isDone));
                } else if (details[0].equals("E")) {
                    taskList.add(new Event(details[2], details[3], isDone));
                } else if (details[0].equals("D")) {
                    taskList.add(new Deadline(details[2], details[3], isDone));
                } else {
                    taskList.add(new Task("Task could not be parsed."));
                    // TODO: Find better way to handle parsing error.
                }
            }
            return taskList;
        } catch (FileNotFoundException e) {
            throw new DukeException("No such file was found.");
        }
    }

    /**
     * Writes the tasks in the given list to an external data file.
     * @param tasks             List of tasks to be written.
     * @throws IOException      If file cannot be found, etc.
     */
    public void writeToFile(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(dataFile);
        fileWriter.write(tasks.toString());
        fileWriter.close();
    }
}
