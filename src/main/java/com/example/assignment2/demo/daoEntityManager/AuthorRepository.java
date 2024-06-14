package com.example.assignment2.demo.daoEntityManager;


import com.example.assignment2.demo.entities.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    List<Author> findAll();
    Optional<Author> findById(Long id);
    Author save(Author author);
    void deleteById(Long id);
    boolean existsById(Long id);
}
