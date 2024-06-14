package com.example.assignment2.demo.service;


import com.example.assignment2.demo.entities.Author;
import com.example.assignment2.demo.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    /**
     * Fetch all authors from the database.
     *
     * @return list of all authors
     */
    public List<Author> getAllAuthors() ;

    /**
     * Fetch an author by its ID.
     *
     * @param authorId the ID of the author to fetch
     * @return the author with the specified ID
     * @throws ResourceNotFoundException if the author is not found
     */
    public Optional<Author> getAuthorById(Long authorId) ;

    /**
     * Create a new author.
     *
     * @param author the author to create
     * @return the created author
     */
    public Author createAuthor(Author author) ;

    /**
     * Delete an author by its ID.
     *
     * @param authorId the ID of the author to delete
     * @throws ResourceNotFoundException if the author is not found
     */
    public void deleteAuthor(Long authorId);

    /**
     * Update an existing author.
     *
     * @param authorId the ID of the author to update
     * @param authorDetails the updated author details
     * @return the updated author
     * @throws ResourceNotFoundException if the author is not found
     */
    public Author updateAuthor(Long authorId, Author authorDetails) ;
}
