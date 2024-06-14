package com.example.assignment2.demo.service;


import com.example.assignment2.demo.entities.BookAuthor;
import com.example.assignment2.demo.entities.BookAuthorId;

import java.util.List;

public interface BookAuthorService {

    public List<BookAuthor> getAllBookAuthors();

    public BookAuthor createBookAuthor(BookAuthor bookAuthor) ;

    public void deleteBookAuthor(BookAuthorId bookAuthorId);
}
