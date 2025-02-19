package com.example.Author.repos;

import com.example.Author.entities.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Integer> {

}
