package com.example.Author.service;

import com.example.Author.entities.Author;
import com.example.Author.repos.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return (List<Author>) authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(int id) {
        return authorRepository.findById(id);
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }
}
