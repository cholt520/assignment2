package com.example.assignment2.demo.controller;

import com.example.assignment2.demo.entities.Author;
import com.example.assignment2.demo.service.AuthorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/authors")
@Api(tags = "Authors", description = "Endpoints for managing authors")
public class AuthorController {

    private static final Logger logger = LoggerFactory.getLogger(AuthorController.class);


    @Autowired
    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    /**
     * Fetch all authors.
     *
     * @return list of all authors
     */
    @GetMapping
    @ApiOperation(value = "Get all authors")
    public List<Author> getAllAuthors() {
        logger.info("GET /api/authors - Fetching all authors");
        return authorService.getAllAuthors();
    }

    /**
     * Fetch an author by its ID.
     *
     * @param id the ID of the author to fetch
     * @return the author with the specified ID
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "Get an author by ID")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        logger.info("GET /api/authors/{} - Fetching author by id", id);
        return authorService.getAuthorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create a new author.
     *
     * @param author the author to create
     * @return the created author
     */
    @PostMapping
    @ApiOperation(value = "Create a new author")
    public Author createAuthor(@RequestBody Author author) {
        logger.info("POST /api/authors - Creating new author: {} {}", author.getFirstName(), author.getLastName());
        return authorService.createAuthor(author);
    }

    /**
     * Update an existing author.
     *
     * @param id the ID of the author to update
     * @param authorDetails the updated author details
     * @return the updated author
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "Update an existing author")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author authorDetails) {
        logger.info("PUT /api/authors/{} - Updating author", id);
        return ResponseEntity.ok(authorService.updateAuthor(id, authorDetails));
    }

    /**
     * Delete an author by its ID.
     *
     * @param id the ID of the author to delete
     * @return a response indicating the deletion status
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an author by ID")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        logger.info("DELETE /api/authors/{} - Deleting author", id);
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}

