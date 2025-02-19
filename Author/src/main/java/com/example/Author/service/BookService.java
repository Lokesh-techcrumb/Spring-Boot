package com.example.Author.service;

import com.example.Author.entities.Books;
import com.example.Author.repos.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BooksRepository bookRepository;

    public List<Books> getAllBooks() {
        return (List<Books>) bookRepository.findAll();
    }

    public Optional<Books> getBookById(int id) {
        return bookRepository.findById(id);
    }

    public Books saveBook(Books book) {
        return bookRepository.save(book);
    }
}