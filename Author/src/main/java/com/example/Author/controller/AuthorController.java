package com.example.Author.controller;

import com.example.Author.entities.Author;
import com.example.Author.service.AuthorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public Optional<Author> getAuthorById(@PathVariable int id) {
        return authorService.getAuthorById(id);
    }

    @PostMapping
    public Author createAuthor(@RequestBody Author author) {

//        Author m = authorService.saveAuthor(author);
//        System.out.println("\n author res : " + m);
//
//        return m;
        return authorService.saveAuthor(author);
    }

}
