package com.example.backend.repos;

import com.example.backend.entities.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long>, PagingAndSortingRepository<Todo,Long> {

    @Query("SELECT t FROM Todo t JOIN t.tag tag WHERE tag.name = :tag AND t.status = :status")
    List<Todo> findByStatusAndTags_Name(String status, String tagName, Pageable pageable);
    Page<Todo> findAll(Pageable pageable);
}
