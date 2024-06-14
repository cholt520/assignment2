package com.example.assignment2.demo.dao;


import com.example.assignment2.demo.entities.BookAuthor;
import com.example.assignment2.demo.entities.BookAuthorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookAuthorRepository extends JpaRepository<BookAuthor, BookAuthorId> {
}

