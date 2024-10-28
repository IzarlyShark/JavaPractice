package org.example.demo1.book.repository;

import org.example.demo1.book.entity.Book;
import org.example.demo1.exeptions.RepositoryException;
import org.example.demo1.utils.DatabaseManager;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private final DatabaseManager dbM;

    private static final String SAVE_BOOK_SQL = """
            INSERT INTO BOOKS(isbn, title, author, published_date)
                                VALUES(?, ?, ?, ?)
                    RETURNING ID""";
    private static final String EXISTS_BY_ISBN_SQL = "SELECT 1 FROM BOOKS WHERE isbn = ?";
    private static final String FIND_BY_TITLE_SQL = """
            SELECT id,isbn, title, author, published_date
            FROM BOOKS WHERE title = ?""";
    private static final String DELETE_BY_ID_SQL = "DELETE FROM BOOKS WHERE id = ?";
    private static final String GET_ALL_BOOKS_SQL = "SELECT id, isbn, title, author, published_date from BOOKS";
    public BookRepository(DatabaseManager dbM) {
        this.dbM = dbM;
    }

    public void save(Book book) {
        var conn = dbM.getConnection();
        try (var stmt = conn.prepareStatement(SAVE_BOOK_SQL, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, book.getIsbn());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthor());
            stmt.setDate(4, book.getPublishedDate() == null ? null : Date.valueOf(book.getPublishedDate()));
            int insertedRow = stmt.executeUpdate();
            if (insertedRow > 0) {
                try (var generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        book.setId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }

    }

    public boolean existsByIsbn(String isbn) {
        var conn = dbM.getConnection();
        try (var stmt = conn.prepareStatement(EXISTS_BY_ISBN_SQL)) {
            stmt.setString(1, isbn);
            stmt.executeQuery();
            try (var rs = stmt.getResultSet()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Book> findByTitle(String title) {
        List<Book> books = new ArrayList<>();
        var conn = dbM.getConnection();
        try (var stmt = conn.prepareStatement(FIND_BY_TITLE_SQL)) {
            stmt.setString(1, title);
            stmt.executeQuery();
            try (var rs = stmt.getResultSet()) {
                while (rs.next()) {
                    books.add(BookRowMapper.mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        var conn = dbM.getConnection();
        try (var stmt = conn.prepareStatement(GET_ALL_BOOKS_SQL)) {
            stmt.executeQuery();
            try (var rs = stmt.getResultSet()) {
                while (rs.next()) {
                    books.add(BookRowMapper.mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    public boolean deleteById(int id) {
        var conn = dbM.getConnection();
        try (var stmt = conn.prepareStatement(DELETE_BY_ID_SQL)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


