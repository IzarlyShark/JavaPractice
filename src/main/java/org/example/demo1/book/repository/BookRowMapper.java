package org.example.demo1.book.repository;

import org.example.demo1.book.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class BookRowMapper {
    public static Book mapRow(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String title = rs.getString("title");
        String author = rs.getString("author");
        LocalDate publishedDate = rs.getDate("published_date") == null
                ? null
                : rs.getDate("published_date").toLocalDate();
        String isbn = rs.getString("isbn");
        return new Book(id, isbn, title, author, publishedDate);

    }
}
