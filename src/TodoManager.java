import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TodoManager {
    private final TodoRepository todoRepository = new TodoRepository();
    private final Scanner scanner =new Scanner(System.in);
    private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void displayTodos() {
        todoRepository.displayTodos();
    }

    public void createTodo() {
        System.out.println("Title: ");
        String title = scanner.nextLine();
        LocalDate dueDate = promptForDate("Until (yyyy-MM-dd): ");
        todoRepository.addTodo(new Todo(title, dueDate));
        System.out.println("Saved!!");
    }

    public void editTodo() {
        int index = promptForTodoNumber("Edit TODO number: ");
        Todo todo = todoRepository.getTodoAt(index);
        if (todo == null) return;

        System.out.println("Title: " + todo.getTitle());
        String title = scanner.nextLine();
        if(!title.isEmpty()) todo.setTitle(title);

        LocalDate dueDate = promptForDate("Until (" + todo.getDueDate(), true);
        if (dueDate != null) todo.setDueDate(dueDate);

        System.out.println("Saved!");
    }

    public void finishTodo() {
        int index = promptForTodoNumber("Finish TODO number: ");
        Todo todo = todoRepository.getTodoAt(index);
        if (todo == null) return;

        todo.setDone();
        System.out.println("Done!");
    }

    public void deleteTodo() {
        int index = promptForTodoNumber("Delete TODO number: ");
        if (todoRepository.removeTodoAt(index)) {
            System.out.println("Deleted!!");
        }
        else {
            System.out.println("Error!!");
        }
    }

    public void saveTodos() {
        todoRepository.saveTodos();
    }

    private int promptForTodoNumber(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
            return -1;
        }
    }

    private LocalDate promptForDate(String prompt) {
        return promptForDate(prompt, false);
    }

    private LocalDate promptForDate(String prompt, boolean allowEmpty) {
        System.out.print(prompt);
        while (true) {
            String input = scanner.nextLine();
            if (allowEmpty && input.isEmpty()) return null;
            try {
                return LocalDate.parse(input, DATE_FORMAT);
            } catch (Exception e) {
                System.out.print("Invalid date format. Please enter again (yyyy-mm-dd): ");
            }
        }
    }
}
