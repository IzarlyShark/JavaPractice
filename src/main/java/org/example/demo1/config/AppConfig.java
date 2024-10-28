package org.example.demo1.config;


import org.example.demo1.reader.repository.ReaderRepository;
import org.example.demo1.reader.service.ReaderService;
import org.example.demo1.ui.console.BookManagementConsoleUI;
import org.example.demo1.book.repository.BookRepository;
import org.example.demo1.book.service.BookService;
import org.example.demo1.ui.console.MainMenuConsoleUI;
import org.example.demo1.ui.console.ReaderManagementConsoleUI;
import org.example.demo1.utils.DatabaseManager;

public final class AppConfig {
    private static final String dbUrl = "jdbc:postgresql://localhost:5432/library_db";
    private static final String dbLogin = "admin";
    private static final String dbPassword = "hello88sharky";

    private static DatabaseManager databaseManager;

    private static BookRepository bookRepository;
    private static BookService bookService;
    private static BookManagementConsoleUI bookManagementConsoleUI;

    private static ReaderRepository readerRepository;
    private static ReaderService readerService;
    private static ReaderManagementConsoleUI readerManagementConsoleUI;


    private static MainMenuConsoleUI mainMenuConsoleUI;

    private AppConfig() {
    }

    public static DatabaseManager getDatabaseManager() {
        if (databaseManager == null) {
            databaseManager = new DatabaseManager(dbUrl, dbLogin, dbPassword);
        }
        return databaseManager;
    }

    public static BookRepository getBookRepository() {
        if (bookRepository == null) {
            bookRepository = new BookRepository(getDatabaseManager());
        }
        return bookRepository;
    }

    public static BookService getBookService() {
        if (bookService == null) {
            bookService = new BookService(getBookRepository(), getDatabaseManager());
        }
        return bookService;
    }

    public static BookManagementConsoleUI getBookManagementConsoleUI() {
        if (bookManagementConsoleUI == null) {
            bookManagementConsoleUI = new BookManagementConsoleUI(getBookService());
        }
        return bookManagementConsoleUI;
    }

    public static ReaderRepository getReaderRepository() {
        if (readerRepository == null) {
            readerRepository = new ReaderRepository(getDatabaseManager());
        }
        return readerRepository;
    }

    public static ReaderService getReaderService() {
        if (readerService == null) {
            readerService = new ReaderService(getReaderRepository(), getDatabaseManager());
        }
        return readerService;
    }

    public static ReaderManagementConsoleUI getReaderManagementConsoleUI() {
        if (readerManagementConsoleUI == null) {
            readerManagementConsoleUI = new ReaderManagementConsoleUI(getReaderService());
        }
        return readerManagementConsoleUI;
    }


    public static MainMenuConsoleUI getMainMenuConsoleUI() {
        if (mainMenuConsoleUI == null) {
            mainMenuConsoleUI = new MainMenuConsoleUI(getBookManagementConsoleUI(), getReaderManagementConsoleUI());
        }
        return mainMenuConsoleUI;
    }
}

