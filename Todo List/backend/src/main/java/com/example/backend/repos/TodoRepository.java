package com.example.backend.repos;

import com.example.backend.entities.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long>, PagingAndSortingRepository<Todo,Long> {

    @Query("SELECT t FROM Todo t JOIN t.tag tag WHERE " +
            "LOWER(t.description) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(tag.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(t.status) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<Todo> searchTodos(@Param("query") String query, Pageable pageable);


    @Query("SELECT t FROM Todo t JOIN t.tag tag WHERE tag.name = :tagName")
    Page<Todo> findByTag_Name(String tagName, Pageable pageable);

    @Query("SELECT t FROM Todo t JOIN t.tag tag WHERE (:tagName IS NULL OR tag.name = :tagName) AND (:status IS NULL OR t.status = :status)")
    Page<Todo> findByStatusAndTags_Name(@Param("status") String status, @Param("tagName") String tagName, Pageable pageable);

    Page<Todo> findAll(Pageable pageable);
}
