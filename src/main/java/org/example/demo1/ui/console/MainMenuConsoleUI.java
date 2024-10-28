package org.example.demo1.ui.console;

import java.util.InputMismatchException;
import java.util.Scanner;

import static org.example.demo1.utils.PrintUtils.println;

public class MainMenuConsoleUI {
    private final BookManagementConsoleUI bookManagementConsoleUI;

    public MainMenuConsoleUI(BookManagementConsoleUI bookManagementConsoleUI) {
        this.bookManagementConsoleUI = bookManagementConsoleUI;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        l1:
        while (true) {
            println("Library management console");
            println("Commands:");
            println("\t0. Quit the console");
            println("\t1. Show all books");
            println("\t2. Find books by title");
            println("\t3. Add a new book");
            println("\t4. Delete a book");
            int choice;
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                println("Please enter a valid option!");
                continue;
            }
            try {
                switch (choice) {
                    case 0:
                        break l1;
                    case 1:
                        bookManagementConsoleUI.getAllBooks();
                        break;
                    case 2:
                        bookManagementConsoleUI.findBooksByTitle();
                        break;
                    case 3:
                        bookManagementConsoleUI.addBook();
                        break;
                    case 4:
                        bookManagementConsoleUI.deleteBook();
                        break;
                    default:
                        println("Please enter a valid option!");
                }


            } catch (Exception e) {
                printError(e);
            }

            println("=".repeat(10));
        }

    }

    private void printError(Exception e) {
        println("Error was occured: " + e.getMessage());
    }
}
