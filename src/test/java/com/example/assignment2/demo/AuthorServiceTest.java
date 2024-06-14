package com.example.assignment2.demo;

import com.example.assignment2.demo.dao.AuthorRepository;
import com.example.assignment2.demo.entities.Author;
import com.example.assignment2.demo.exception.ResourceNotFoundException;
import com.example.assignment2.demo.service.impl.AuthorServiceImpl;
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

public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAuthors() {
        Author author1 = new Author();
        author1.setFirstName("Author 1");
        Author author2 = new Author();
        author2.setFirstName("Author 2");

        when(authorRepository.findAll()).thenReturn(Arrays.asList(author1, author2));

        List<Author> authors = authorService.getAllAuthors();
        assertEquals(2, authors.size());
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    public void testGetAuthorById() {
        Author author = new Author();
        author.setAuthorId(1L);
        author.setFirstName("Test Author");

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        Optional<Author> foundAuthor = authorService.getAuthorById(1L);
        assertNotNull(foundAuthor);
        assertEquals("Test Author", foundAuthor.get());
    }

    @Test
    public void testGetAuthorById_NotFound() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            authorService.getAuthorById(1L);
        });
    }

    @Test
    public void testCreateAuthor() {
        Author author = new Author();
        author.setFirstName("New Author");

        when(authorRepository.save(author)).thenReturn(author);

        Author createdAuthor = authorService.createAuthor(author);
        assertNotNull(createdAuthor);
        assertEquals("New Author", createdAuthor.getFirstName());
    }

    @Test
    public void testDeleteAuthor() {
        Author author = new Author();
        author.setAuthorId(1L);

        when(authorRepository.existsById(1L)).thenReturn(true);
        doNothing().when(authorRepository).deleteById(1L);

        authorService.deleteAuthor(1L);
        verify(authorRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteAuthor_NotFound() {
        when(authorRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> {
            authorService.deleteAuthor(1L);
        });
    }

    @Test
    public void testUpdateAuthor() {
        Author author = new Author();
        author.setAuthorId(1L);
        author.setFirstName("Updated Author");

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(authorRepository.save(author)).thenReturn(author);

        Author updatedAuthor = authorService.updateAuthor(1L, author);
        assertNotNull(updatedAuthor);
        assertEquals("Updated Author", updatedAuthor.getFirstName());
    }

    @Test
    public void testUpdateAuthor_NotFound() {
        Author authorDetails = new Author();
        authorDetails.setFirstName("Updated Author");

        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            authorService.updateAuthor(1L, authorDetails);
        });
    }
}
