package com.example.Author.repos;

import com.example.Author.entities.Books;
import org.springframework.data.repository.CrudRepository;

public interface BooksRepository extends CrudRepository<Books, Integer> {
}
