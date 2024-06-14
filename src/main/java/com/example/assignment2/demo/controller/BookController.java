package com.example.assignment2.demo.controller;

import com.example.assignment2.demo.entities.Book;
import com.example.assignment2.demo.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Api(tags = "Books", description = "Endpoints for managing books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    /**
     * Fetch all books.
     *
     * @return list of all books
     */
    @GetMapping
    @ApiOperation(value = "Get all books")
    public List<Book> getAllBooks() {
        logger.info("GET /api/books - Fetching all books");
        return bookService.getAllBooks();
    }

    /**
     * Fetch a book by its ID.
     *
     * @param id the ID of the book to fetch
     * @return the book with the specified ID
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "Get a book by ID")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        logger.info("GET /api/books/{} - Fetching book by id", id);
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create a new book.
     *
     * @param book the book to create
     * @return the created book
     */
    @PostMapping
    @ApiOperation(value = "Create a new book")
    public Book createBook(@RequestBody Book book) {
        logger.info("POST /api/books - Creating new book: {}", book.getTitle());
        return bookService.createBook(book);
    }

    /**
     * Update an existing book.
     *
     * @param id the ID of the book to update
     * @param bookDetails the updated book details
     * @return the updated book
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "Update an existing book")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        logger.info("PUT /api/books/{} - Updating book", id);
        return ResponseEntity.ok(bookService.updateBook(id, bookDetails));
    }

    /**
     * Delete a book by its ID.
     *
     * @param id the ID of the book to delete
     * @return a response indicating the deletion status
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a book by ID")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        logger.info("DELETE /api/books/{} - Deleting book", id);
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
