package bob;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Creates a command object that operates on task i of the tasklist.
     *
     * @param taskIndex index of task.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            Task task = tasks.remove(taskIndex);
            storage.save(tasks);
            return String.format("""
                    Oof. I have removed the requested task:
                    \t%s
                    Now you have %s tasks in the list""", task, tasks.size());

        } catch (IndexOutOfBoundsException e) {
            return "Hm, you don't seem to have that task";
        } catch (IOException e) {
            return "I can't remember that for some reason T T";
        }
    }
}