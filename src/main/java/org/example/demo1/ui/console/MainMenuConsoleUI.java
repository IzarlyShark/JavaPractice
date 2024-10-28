package org.example.demo1.ui.console;

import java.util.InputMismatchException;
import java.util.Scanner;

import static org.example.demo1.utils.PrintUtils.println;

public class MainMenuConsoleUI {
    private final BookManagementConsoleUI bookManagementConsoleUI;
    private final ReaderManagementConsoleUI readerManagementConsoleUI;

    public MainMenuConsoleUI(BookManagementConsoleUI bookManagementConsoleUI, ReaderManagementConsoleUI readerManagementConsoleUI) {
        this.bookManagementConsoleUI = bookManagementConsoleUI;
        this.readerManagementConsoleUI = readerManagementConsoleUI;
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
            println("\t5. Show all readers");
            println("\t6. Find readers by email");
            println("\t7. Add a new readers");
            println("\t8. Delete a reader");

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
                    case 5:
                        readerManagementConsoleUI.getAllReaders();
                        break;
                    case 6:
                        readerManagementConsoleUI.findReadersByEmail();
                        break ;
                    case 7:
                        readerManagementConsoleUI.addReader();
                        break;
                    case 8:
                        readerManagementConsoleUI.deleteReader();
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
