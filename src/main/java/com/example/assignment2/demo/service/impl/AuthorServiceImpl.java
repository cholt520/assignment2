package com.example.assignment2.demo.service.impl;


import com.example.assignment2.demo.dao.AuthorRepository;
import com.example.assignment2.demo.entities.Author;
import com.example.assignment2.demo.exception.ResourceNotFoundException;
import com.example.assignment2.demo.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);


    @Autowired
    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    /**
     * Fetch all authors from the database.
     *
     * @return list of all authors
     */
    public List<Author> getAllAuthors() {
        logger.info("Fetching all authors");
        return authorRepository.findAll();
    }
    /**
     * Fetch an author by its ID.
     *
     * @param authorId the ID of the author to fetch
     * @return the author with the specified ID
     * @throws ResourceNotFoundException if the author is not found
     */
    public Optional<Author> getAuthorById(Long authorId) {
        logger.info("Fetching author with id {}", authorId);
        return Optional.ofNullable(authorRepository.findById(authorId)
                .orElseThrow(() -> {
                    logger.error("Author not found with id {}", authorId);
                    return new ResourceNotFoundException("Author not found with id " + authorId);
                }));
    }

    /**
     * Create a new author.
     *
     * @param author the author to create
     * @return the created author
     */
    public Author createAuthor(Author author) {
        logger.info("Creating new author: {} {}", author.getFirstName(), author.getLastName());
        return authorRepository.save(author);
    }

    /**
     * Delete an author by its ID.
     *
     * @param authorId the ID of the author to delete
     * @throws ResourceNotFoundException if the author is not found
     */
    public void deleteAuthor(Long authorId) {
        if (!authorRepository.existsById(authorId)) {
            logger.error("Author not found with id {}", authorId);
            throw new ResourceNotFoundException("Author not found with id " + authorId);
        }
        logger.info("Deleting author with id {}", authorId);
        authorRepository.deleteById(authorId);
    }

    /**
     * Update an existing author.
     *
     * @param authorId the ID of the author to update
     * @param authorDetails the updated author details
     * @return the updated author
     * @throws ResourceNotFoundException if the author is not found
     */
    public Author updateAuthor(Long authorId, Author authorDetails) {
        logger.info("Updating author with id {}", authorId);
        return authorRepository.findById(authorId).map(author -> {
            author.setFirstName(authorDetails.getFirstName());
            author.setLastName(authorDetails.getLastName());
            author.setBirthdate(authorDetails.getBirthdate());
            logger.info("Updated author with id {}", authorId);
            return authorRepository.save(author);
        }).orElseThrow(() -> {
            logger.error("Author not found with id {}", authorId);
            return new ResourceNotFoundException("Author not found with id " + authorId);
        });    }
}
