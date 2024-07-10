import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TodoManager todoManager = new TodoManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            todoManager.displayTodos();

            System.out.println("\n1. Create TODO");
            System.out.println("2. Edit TODO");
            System.out.println("3. Finish TODO");
            System.out.println("4. Delete TODO");
            System.out.println("5. Exit");
            System.out.print("\nInput: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    todoManager.createTodo();
                    break;
                case "2":
                    todoManager.editTodo();
                    break;
                case "3":
                    todoManager.finishTodo();
                    break;
                case "4":
                    todoManager.deleteTodo();
                    break;
                case "5":
                    todoManager.saveTodos();
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
