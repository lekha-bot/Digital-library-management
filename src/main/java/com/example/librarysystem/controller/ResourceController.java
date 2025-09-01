package com.example.librarysystem.controller;

import com.example.librarysystem.dto.AuthorDto;
import com.example.librarysystem.dto.BookDto;
import com.example.librarysystem.dto.ResourceDto;
import com.example.librarysystem.entity.Book;
import com.example.librarysystem.entity.Resource;
import com.example.librarysystem.repository.BookRepository;
import com.example.librarysystem.repository.ResourceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    private final ResourceRepository resourceRepository;
    private final BookRepository bookRepository;

    public ResourceController(ResourceRepository resourceRepository, BookRepository bookRepository) {
        this.resourceRepository = resourceRepository;
        this.bookRepository = bookRepository;
    }

    // ✅ Create Resource
    @PostMapping
    public ResourceDto createResource(@RequestBody Resource resource) {
        Book book = bookRepository.findById(resource.getBook().getId())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        resource.setBook(book);
        Resource saved = resourceRepository.save(resource);
        return mapToDto(saved);
    }

    // ✅ Get All Resources
    @GetMapping
    public List<ResourceDto> getAllResources() {
        return resourceRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    // ✅ Get Resource by ID
    @GetMapping("/{id}")
    public ResourceDto getResourceById(@PathVariable Long id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found"));
        return mapToDto(resource);
    }

    // ✅ Update Resource
    @PutMapping("/{id}")
    public ResourceDto updateResource(@PathVariable Long id, @RequestBody Resource resourceDetails) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        resource.setResourceType(resourceDetails.getResourceType());
        resource.setResourceUrl(resourceDetails.getResourceUrl());

        // If book is updated, fetch from DB
        if (resourceDetails.getBook() != null && resourceDetails.getBook().getId() != null) {
            Book book = bookRepository.findById(resourceDetails.getBook().getId())
                    .orElseThrow(() -> new RuntimeException("Book not found"));
            resource.setBook(book);
        }

        Resource updated = resourceRepository.save(resource);
        return mapToDto(updated);
    }

    // ✅ Delete Resource
    @DeleteMapping("/{id}")
    public String deleteResource(@PathVariable Long id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        resourceRepository.delete(resource);
        return "Resource with ID " + id + " has been deleted successfully!";
    }


    // ✅ Mapper: Convert Entity → DTO with safe null checks
    private ResourceDto mapToDto(Resource resource) {
        // Author DTO
        AuthorDto authorDto = null;
        if (resource.getBook() != null && resource.getBook().getAuthor() != null) {
            authorDto = new AuthorDto(
                    resource.getBook().getAuthor().getId(),
                    resource.getBook().getAuthor().getName(),
                    resource.getBook().getAuthor().getBiography()
            );
        }

        // Book DTO
        BookDto bookDto = null;
        if (resource.getBook() != null) {
            bookDto = new BookDto(
                    resource.getBook().getId(),
                    resource.getBook().getTitle(),
                    resource.getBook().getIsbn(),
                    authorDto
            );
        }

        // Resource DTO
        return new ResourceDto(
                resource.getId(),
                resource.getResourceType(),
                resource.getResourceUrl(),
                bookDto
        );
    }
}
