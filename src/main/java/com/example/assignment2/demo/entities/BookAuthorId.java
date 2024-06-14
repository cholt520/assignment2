package com.example.assignment2.demo.entities;

import java.io.Serializable;

public class BookAuthorId implements Serializable {
    private Long book;
    private Long author;

    // Default constructor, equals, and hashCode methods
    public BookAuthorId() {
    }

    public BookAuthorId(Long book, Long author) {
        this.book = book;
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookAuthorId that = (BookAuthorId) o;

        if (!book.equals(that.book)) return false;
        return author.equals(that.author);
    }

    @Override
    public int hashCode() {
        int result = book.hashCode();
        result = 31 * result + author.hashCode();
        return result;
    }
}
