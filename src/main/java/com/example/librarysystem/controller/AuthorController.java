package com.example.librarysystem.controller;

import com.example.librarysystem.entity.Author;
import com.example.librarysystem.repository.AuthorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // ✅ Create new Author
    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        // ignore id if passed accidentally
        author.setId(null);
        Author savedAuthor = authorRepository.save(author);
        return ResponseEntity.ok(savedAuthor);
    }

    // ✅ Get all Authors
    @GetMapping
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    // ✅ Get Author by ID
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        return authorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Update Author
    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(
            @PathVariable Long id,
            @RequestBody Author authorDetails
    ) {
        return authorRepository.findById(id)
                .map(author -> {
                    author.setName(authorDetails.getName());
                    author.setBiography(authorDetails.getBiography());
                    Author updated = authorRepository.save(author);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Delete Author
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAuthor(@PathVariable Long id) {
        return authorRepository.findById(id)
                .map(author -> {
                    authorRepository.delete(author);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}

