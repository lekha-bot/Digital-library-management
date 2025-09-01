package com.example.librarysystem.dto;

public class BookDto {
    private Long id;
    private String title;
    private String isbn;
    private AuthorDto author;

    // ✅ Default constructor (needed for JSON serialization)
    public BookDto() {}

    // ✅ All-args constructor (the one we need)
    public BookDto(Long id, String title, String isbn, AuthorDto author) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.author = author;
    }

    // ✅ Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public AuthorDto getAuthor() {
        return author;
    }
    public void setAuthor(AuthorDto author) {
        this.author = author;
    }
}
