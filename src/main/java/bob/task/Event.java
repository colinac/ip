package bob.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.to = to;
        this.from = from;
    }

    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone, LocalDateTime createdAt) {
        super(description, isDone, createdAt);
        this.to = to;
        this.from = from;
    }

    public static Task from(String text) {
        String[] parameters = text.split("\\s\\|\\s");
        assert parameters.length == 6 : "Number of elements after splitting should be 6";
        return new Event(
                parameters[2],
                LocalDateTime.parse(parameters[3]),
                LocalDateTime.parse(parameters[4]),
                parameters[1].equals("1"),
                LocalDateTime.parse(parameters[5]));

    }

    @Override
    public String toText() {
        return String.format(
                "E | %s | %s | %s | %s | %s",
                super.isDone ? 1 : 0,
                super.description,
                this.from,
                this.to,
                super.getCreatedAt());
    }

    @Override
    public String toString() {

        return String.format(
                "[E]%s (from: %s to: %s)",
                super.toString(),
                this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")),
                this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")));
    }

}
