package com.example.backend.controller;

import com.example.backend.entities.Tag;
import com.example.backend.repos.TagRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
@CrossOrigin(origins = "http://localhost:5173")
class TagController {
    private final TagRepository tagRepository;

    public TagController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @GetMapping
    public List<Tag> getAllTags() {
        return (List<Tag>) tagRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {
        if (tag.getName() == null || tag.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().build(); // Validation: Tag name must not be empty
        }
        Tag savedTag = tagRepository.save(tag);
        return ResponseEntity.ok(savedTag);
    }
}

