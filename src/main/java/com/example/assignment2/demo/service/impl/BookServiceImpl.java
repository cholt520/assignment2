package com.example.assignment2.demo.service.impl;

import com.example.assignment2.demo.dao.BookRepository;
import com.example.assignment2.demo.entities.Book;
import com.example.assignment2.demo.exception.ResourceNotFoundException;
import com.example.assignment2.demo.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);


    @Autowired
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Fetch all books from the database.
     *
     * @return list of all books
     */
    public List<Book> getAllBooks() {
        logger.info("Fetching all books");
        return bookRepository.findAll();
    }

    /**
     * Fetch a book by its ID.
     *
     * @param bookId the ID of the book to fetch
     * @return the book with the specified ID
     * @throws ResourceNotFoundException if the book is not found
     */
    public Optional<Book> getBookById(Long bookId) {
        logger.info("Fetching book with id {}", bookId);
        return Optional.ofNullable(bookRepository.findById(bookId).
                orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + bookId)));
    }

    /**
     * Create a new book.
     *
     * @param book the book to create
     * @return the created book
     */
    public Book createBook(Book book) {
        logger.info("Creating new book: {}", book.getTitle());
        return bookRepository.save(book);
    }

    /**
     * Delete a book by its ID.
     *
     * @param bookId the ID of the book to delete
     * @throws ResourceNotFoundException if the book is not found
     */
    public void deleteBook(Long bookId) {
        if (!bookRepository.existsById(bookId)) {
            logger.error("Book not found with id {}", bookId);
            throw new ResourceNotFoundException("Book not found with id " + bookId);
        }
        logger.info("Deleting book with id {}", bookId);
        bookRepository.deleteById(bookId);
    }

    /**
     * Update an existing book.
     *
     * @param bookId the ID of the book to update
     * @param bookDetails the updated book details
     * @return the updated book
     * @throws ResourceNotFoundException if the book is not found
     */
    public Book updateBook(Long bookId, Book bookDetails) {
        logger.info("Updating book with id {}", bookId);
        return bookRepository.findById(bookId).map(book -> {
            book.setTitle(bookDetails.getTitle());
            book.setPublicationYear(bookDetails.getPublicationYear());
            book.setGenre(bookDetails.getGenre());
            logger.info("Updated book with id {}", bookId);
            return bookRepository.save(book);
        }).orElseThrow(() -> {
            logger.error("Book not found with id {}", bookId);
            return new ResourceNotFoundException("Book not found with id " + bookId);
        });
    }
}
