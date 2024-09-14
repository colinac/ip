package bob.task;

import java.time.LocalDateTime;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;

    /**
     * Creates a task from text that is correctly formatted.
     *
     * @param text correctly formatted text from which tasks can be created.
     * @return Created task.
     */
    public static Task from(String text) {
        if (text.startsWith("T")) {
            return ToDo.from(text);
        }
        if (text.startsWith("E")) {
            return Event.from(text);
        }
        if (text.startsWith("D")) {
            return Deadline.from(text);
        }
        return null;
    }

    /**
     * Converts task into the format used to create them.
     *
     * @return Correctly formatted text from which tasks can be created using the from() method.
     */
    public abstract String toText();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.createdAt = LocalDateTime.now();

    }

    public Task(String description, boolean isDone, LocalDateTime createdAt, LocalDateTime completedAt) {
        this.description = description;
        this.isDone = isDone;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getCompletedAt() {
        return this.completedAt;
    }

    public String getStatusIcon() {
        String icon = isDone ? "X" : " ";
        assert isDone ? icon.equals("X") : icon.equals(" ");
        return icon;
    }

    /**
     * Marks a task as completed.
     */
    public void mark(LocalDateTime completedAt) {
        this.isDone = true;
        this.completedAt = completedAt;
    }

    /**
     * Undoes the marking of a task.
     */
    public void unmark() {
        this.isDone = false;
        this.completedAt = null;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}