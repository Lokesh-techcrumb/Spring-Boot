package com.example.backend.controller;

import com.example.backend.entities.Tag;
import com.example.backend.entities.Todo;
import com.example.backend.repos.TagRepository;
import com.example.backend.repos.TodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
@CrossOrigin(origins = "http://localhost:5173")
class TodoController {
    private final TodoRepository todoRepository;
    private final TagRepository tagRepository;

    public TodoController(TodoRepository todoRepository, TagRepository tagRepository) {
        this.todoRepository = todoRepository;
        this.tagRepository = tagRepository;
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        Tag tag = tagRepository.findByName(todo.getTag().getName());
        if (tag == null) {
            tag = tagRepository.save(new Tag(todo.getTag().getName()));
        }
        todo.setTag(tag);
        return todoRepository.save(todo);
    }

    @GetMapping
    public Page<Todo> getTodos(@RequestParam(required = false) String status,
                               @RequestParam(required = false) String tag,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (status != null && tag != null) {
            return (Page<Todo>) todoRepository.findByStatusAndTags_Name(status, tag, pageable);
        }
        return (Page<Todo>)  todoRepository.findAll(pageable);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody Todo todoDetails) {
        return todoRepository.findById(id).map(todo -> {
            todo.setDescription(todoDetails.getDescription());
            todo.setStatus(todoDetails.getStatus());
            Tag tag = tagRepository.findByName(todoDetails.getTag().getName());
            if (tag == null) {
                tag = tagRepository.save(new Tag(todoDetails.getTag().getName()));
            }
            todo.setTag(tag);

            return todoRepository.save(todo);
        }).orElseThrow(() -> new RuntimeException("Todo not found"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        return todo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoRepository.deleteById(id);
    }
}
