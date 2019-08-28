package duke.task;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void add(Task newTask) {
        tasks.add(newTask);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    @Override
    public String toString() {
        String output = "";
        for (Task task : tasks) {
            output = output.concat(task.formatAsData() + "\n");
        }
        return output;
    }
}
