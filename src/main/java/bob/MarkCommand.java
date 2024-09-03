package bob;

import java.io.IOException;

public class MarkCommand extends Command {
    private int taskIndex;

    /**
     * Creates a command object that operates on task i of the tasklist.
     *
     * @param taskIndex index of task.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            Task task = tasks.getTask(taskIndex);
            task.mark();
            storage.save(tasks);
            return String.format("Nice! I've marked this task as done:\n\t%s", task);
        } catch (IndexOutOfBoundsException e) {
            return "Nice try but there's no such task.";
        } catch (IOException e) {
            return "I can't remember that for some reason T T";
        }
    }
}
