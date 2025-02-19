package com.example.backend.repos;


import com.example.backend.entities.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {
    Tag findByName(String name);
}