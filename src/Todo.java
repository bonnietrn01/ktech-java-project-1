import java.time.LocalDate;

public class Todo {
    private String title;
    private LocalDate dueDate;
    private boolean isDone;

    public Todo(String title, LocalDate dueDate) {
        this.title = title;
        this.dueDate = dueDate;
        this.isDone = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return title + "(Due: " + dueDate + ")" + (isDone ? " (Done)" : "");
    }
}
