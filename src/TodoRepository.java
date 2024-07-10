import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TodoRepository {
    private final List<Todo> todos = new ArrayList<>();
    private static final String fileName = "todos.txt";
    private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public TodoRepository() {
        loadTodos();
    }

    public void displayTodos() {
        System.out.println("\nWelcome!");

        long remainingTodos = todos.stream()
                .filter(todo -> !todo.isDone() && !todo.getDueDate().isBefore(LocalDate.now()))
                .count();

        if (remainingTodos == 0) {
            System.out.println("\nYou have no more TODOs left!");
        } else if (remainingTodos == 1) {
            System.out.println("\nYou have 1 TODO left!");
        } else {
            System.out.println("\nYou have " + remainingTodos + " TODOs left!");
        }

        for (int i = 0; i < todos.size(); i++) {
            Todo todo = todos.get(i);
            if (!todo.getDueDate().isBefore(LocalDate.now()) || !todo.isDone()) {
                System.out.println((i +1) + ". " + todo);
            }
        }
    }

    public void addTodo(Todo todo) {
    todos.add(todo);
    }

    public Todo getTodoAt(int index) {
        if (index < 0 || index >= todos.size()) {
            System.out.println("Invalid TODO number");
            return null;
        }
        return todos.get(index);
    }

    public boolean removeTodoAt(int index) {
        if(index < 0 || index >= todos.size()) {
            return false;
        }
        todos.remove(index);
        return true;
    }

    public void saveTodos() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (Todo todo : todos) {
                writer.write(todo.getTitle() + "," + todo.getDueDate() + "," + todo.isDone());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTodos() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String title = parts[0];
                LocalDate dueDate = LocalDate.parse(parts[1], DATE_FORMAT);
                boolean isDone = Boolean.parseBoolean(parts[2]);
                Todo todo= new Todo(title, dueDate);
                if (isDone) {
                    todo.setDone();
                }
                todos.add(todo);
            }
        } catch (IOException e) {
        }
    }
}
