package com.example.assignment2.demo.service;


import com.example.assignment2.demo.entities.Book;
import com.example.assignment2.demo.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface BookService {

    /**
     * Fetch all books from the database.
     *
     * @return list of all books
     */
    public List<Book> getAllBooks() ;

    /**
     * Fetch a book by its ID.
     *
     * @param bookId the ID of the book to fetch
     * @return the book with the specified ID
     */
    public Optional<Book> getBookById(Long bookId) ;

    /**
     * Create a new book.
     *
     * @param book the book to create
     * @return the created book
     */
    public Book createBook(Book book) ;

    /**
     * Delete a book by its ID.
     *
     * @param bookId the ID of the book to delete
     * @throws ResourceNotFoundException if the book is not found
     */
    public void deleteBook(Long bookId);

    /**
     * Update an existing book.
     *
     * @param bookId the ID of the book to update
     * @param bookDetails the updated book details
     * @return the updated book
     * @throws ResourceNotFoundException if the book is not found
     */
    public Book updateBook(Long bookId, Book bookDetails);
}
