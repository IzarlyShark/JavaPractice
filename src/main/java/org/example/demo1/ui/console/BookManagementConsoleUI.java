package org.example.demo1.ui.console;

import org.example.demo1.book.service.BookService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static org.example.demo1.utils.PrintUtils.print;
import static org.example.demo1.utils.PrintUtils.println;

public class BookManagementConsoleUI {
    private final BookService bookService;

    public BookManagementConsoleUI(BookService bookService) {
        this.bookService = bookService;
    }

    public void getAllBooks() {
        var books = bookService.getAllBooks();
        if (books.isEmpty()) {
            print("No books found");
            return;
        }
        println("Found " + books.size() + " books");
        for (var book : books) {
            println(book.toString());
        }

    }

    public void findBooksByTitle() {
        Scanner scanner = new Scanner(System.in);
        println("Please enter the title of the book you wish to search:");
        var books = bookService.findBooksByTitle(scanner.nextLine());
        if (books.isEmpty()) {
            print("No books found");
            return;
        }
        println("Found " + books.size() + " books");
        for (var book : books) {
            println(book.toString());
        }
    }

    public void addBook() {
        String title, author, isbn;
        LocalDate date;
        println("If you do not want to enter an optional field, then leave it empty");
        var sc = new Scanner(System.in);
        println("(REQUIRED FIELD) Please enter the title of the book you wish to add:");
        title = sc.nextLine().trim();
        title = title.isEmpty() ? null : title;
        println("Please enter the author of the book you wish to add:");
        author = sc.nextLine().trim();
        author = author.isEmpty() ? null : author;
        println("(REQUIRED FIELD) Please enter the ISBN of the book you wish to add:");
        isbn = sc.nextLine().trim();
        isbn = isbn.isEmpty() ? null : isbn;
        println("Please enter the date of the book you wish to add (for example 2016-08-16):");
        String dateStr = sc.nextLine().trim();
        try {
            if (dateStr.isEmpty())
                date = null;
            else
                date = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            println("Not a valid date");
            return;
        }
        bookService.addBook(title, author, date, isbn);
        println("Book successfully added");
    }

    public void deleteBook() {
        println("Please enter the id of the book you wish to delete:");
        Scanner scanner = new Scanner(System.in);
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            print("Id must be an integer");
            return;
        }
        if (bookService.deleteBook(id))
            print("Book deleted");
        else
            print("Book not found");
    }

}
