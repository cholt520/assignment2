package com.example.assignment2.demo;


import com.example.assignment2.demo.dao.BookRepository;
import com.example.assignment2.demo.entities.Book;
import com.example.assignment2.demo.exception.ResourceNotFoundException;
import com.example.assignment2.demo.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBooks() {
        Book book1 = new Book();
        book1.setTitle("Book 1");
        Book book2 = new Book();
        book2.setTitle("Book 2");

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        List<Book> books = bookService.getAllBooks();
        assertEquals(2, books.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void testGetBookById() {
        Book book = new Book();
        book.setBookId(1L);
        book.setTitle("Test Book");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Optional<Book> foundBook = bookService.getBookById(1L);
        assertNotNull(foundBook);
        assertEquals("Test Book", foundBook.get());
    }

    @Test
    public void testGetBookById_NotFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            bookService.getBookById(1L);
        });
    }

    @Test
    public void testCreateBook() {
        Book book = new Book();
        book.setTitle("New Book");

        when(bookRepository.save(book)).thenReturn(book);

        Book createdBook = bookService.createBook(book);
        assertNotNull(createdBook);
        assertEquals("New Book", createdBook.getTitle());
    }

    @Test
    public void testDeleteBook() {
        Book book = new Book();
        book.setBookId(1L);

        when(bookRepository.existsById(1L)).thenReturn(true);
        doNothing().when(bookRepository).deleteById(1L);

        bookService.deleteBook(1L);
        verify(bookRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteBook_NotFound() {
        when(bookRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> {
            bookService.deleteBook(1L);
        });
    }

    @Test
    public void testUpdateBook() {
        Book book = new Book();
        book.setBookId(1L);
        book.setTitle("Updated Book");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);

        Book updatedBook = bookService.updateBook(1L, book);
        assertNotNull(updatedBook);
        assertEquals("Updated Book", updatedBook.getTitle());
    }

    @Test
    public void testUpdateBook_NotFound() {
        Book bookDetails = new Book();
        bookDetails.setTitle("Updated Book");

        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            bookService.updateBook(1L, bookDetails);
        });
    }
}

