package org.example.demo1.book.entity;

import java.time.LocalDate;

public class Book {
    private Integer id;
    private String title;
    private String author;
    private LocalDate publishedDate;
    private String isbn;

    public Book() {

    }

    public Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    public Book(String title, String isbn, String author) {
        this(title, isbn);
        this.author = author;
    }

    public Book(Integer id, String isbn, String title, String author, LocalDate publishedDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishedDate = publishedDate;
        this.isbn = isbn;
    }

    public Book(String isbn, String title, String author, LocalDate publishedDate) {
        this.title = title;
        this.author = author;
        this.publishedDate = publishedDate;
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "ID=%s ISBN=%s TITLE=%s AUTHOR=%s PUBLISHED_DATE=%s"
                .formatted(id, isbn, title, author, publishedDate);
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
