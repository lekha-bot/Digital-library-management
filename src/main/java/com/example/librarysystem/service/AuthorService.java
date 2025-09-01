package com.example.librarysystem.service;

import com.example.librarysystem.entity.Author;
import com.example.librarysystem.repository.AuthorRepository;
import com.example.librarysystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    // Add Author
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    // Get all Authors
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    // Get Author by ID
    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    // Update Author
    public Author updateAuthor(Long id, Author authorDetails) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        author.setName(authorDetails.getName());
        author.setBiography(authorDetails.getBiography());

        return authorRepository.save(author);
    }


    // Delete Author
    public void deleteAuthor(Long id) {
        // Check if author exists
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));

        // Check if books are linked to this author
        if (!bookRepository.findByAuthorId(id).isEmpty()) {
            throw new RuntimeException("Cannot delete author because books are linked. Delete books first.");
        }

        // Safe to delete
        authorRepository.delete(author);
    }
    }

