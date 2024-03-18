package ChT;

import java.util.Scanner;

public class ConsoleListPApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PaginatedListManager<String> paginatedListManager = new PaginatedListManager<>(5);

        boolean exit = false;
        while (!exit) {
            System.out.println("Current Page: " + (paginatedListManager.getCurrentPage() + 1) +
                    "/" + paginatedListManager.getTotalPages());
            for (String item : paginatedListManager.getCurrentPageItems()) {
                System.out.println(item);
            }

            System.out.println("\nOptions: first, last, next, prev, go <page_number>, exit");
            System.out.print("Enter command: ");
            String command = scanner.nextLine();

            switch (command) {
                case "first":
                    paginatedListManager.firstPage();
                    break;
                case "last":
                    paginatedListManager.lastPage();
                    break;
                case "next":
                    paginatedListManager.nextPage();
                    break;
                case "prev":
                    paginatedListManager.prevPage();
                    break;
                case "exit":
                    exit = true;
                    break;
                default:
                    if (command.startsWith("go ")) {
                        try {
                            int page = Integer.parseInt(command.split(" ")[1]) - 1;
                            paginatedListManager.setPage(page);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid page number!");
                        }
                    } else {
                        System.out.println("Invalid command!");
                    }
            }
        }
        scanner.close();
    }
}
