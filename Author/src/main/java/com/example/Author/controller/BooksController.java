package com.example.Author.controller;

import com.example.Author.entities.Books;
import com.example.Author.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Books> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Optional<Books> getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public Books createBook(@RequestBody Books book) {
        return bookService.saveBook(book);
    }
}
