package com.example.assignment2.demo.daoEntityManager;


import com.example.assignment2.demo.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Book save(Book book);
    void deleteById(Long id);
    boolean existsById(Long id);
}
