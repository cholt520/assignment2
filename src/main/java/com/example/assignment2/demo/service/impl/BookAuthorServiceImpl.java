package com.example.assignment2.demo.service.impl;


import com.example.assignment2.demo.dao.BookAuthorRepository;
import com.example.assignment2.demo.entities.BookAuthor;
import com.example.assignment2.demo.entities.BookAuthorId;
import com.example.assignment2.demo.service.BookAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookAuthorServiceImpl implements BookAuthorService {

    @Autowired
    private BookAuthorRepository bookAuthorRepository;


    public BookAuthorServiceImpl(BookAuthorRepository bookAuthorRepository) {
        this.bookAuthorRepository = bookAuthorRepository;
    }

    public List<BookAuthor> getAllBookAuthors() {
        return bookAuthorRepository.findAll();
    }

    public BookAuthor createBookAuthor(BookAuthor bookAuthor) {
        return bookAuthorRepository.save(bookAuthor);
    }

    public void deleteBookAuthor(BookAuthorId bookAuthorId) {
        bookAuthorRepository.deleteById(bookAuthorId);
    }
}
