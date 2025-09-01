package com.example.librarysystem.dto;

public class ResourceDto {
    private Long id;
    private String resourceType;
    private String resourceUrl;
    private BookDto book;

    // Default constructor
    public ResourceDto() {}

    // All-args constructor
    public ResourceDto(Long id, String resourceType, String resourceUrl, BookDto book) {
        this.id = id;
        this.resourceType = resourceType;
        this.resourceUrl = resourceUrl;
        this.book = book;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getResourceType() {
        return resourceType;
    }
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }
    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public BookDto getBook() {
        return book;
    }
    public void setBook(BookDto book) {
        this.book = book;
    }
}
