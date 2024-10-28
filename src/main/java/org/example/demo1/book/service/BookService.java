package org.example.demo1.book.service;

import org.example.demo1.book.entity.Book;
import org.example.demo1.book.repository.BookRepository;
import org.example.demo1.exeptions.ServiceException;
import org.example.demo1.utils.DatabaseManager;

import java.time.LocalDate;
import java.util.List;

public class BookService {
    private final BookRepository bookRepository;
    private final DatabaseManager databaseManager;

    public BookService(BookRepository bookRepository, DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        try {
            databaseManager.openConnection(true);
            return bookRepository.getAll();
        } finally {
            databaseManager.closeConnection();
        }
    }

    public List<Book> findBooksByTitle(String title) {
        try {
            databaseManager.openConnection(true);
            return bookRepository.findByTitle(title);
        } finally {
            databaseManager.closeConnection();
        }

    }

    public void addBook(String title, String author, LocalDate publishedDate, String isbn) {
        if (isbn == null || isbn.isBlank())
            throw new ServiceException("ISBN cannot be blank");
        else if (title == null || title.isBlank())
            throw new ServiceException("Title cannot be blank");
        try {
            databaseManager.openConnection(false);
            if (bookRepository.existsByIsbn(isbn))
                throw new ServiceException("ISBN already exists");
            bookRepository.save(new Book(isbn, title, author, publishedDate));
            databaseManager.commitCurrentConnection();
        } catch (Exception e) {
            if (databaseManager.isOpenConnection())
                databaseManager.rollbackCurrentConnection();
            throw new ServiceException(e);
        } finally {
            //В любом случае соединение закроется
            databaseManager.closeConnection();
        }
    }

    public boolean deleteBook(int id) {
        try {
            databaseManager.openConnection(true);
            return bookRepository.deleteById(id);
        } finally {
            databaseManager.closeConnection();
        }
    }
}
