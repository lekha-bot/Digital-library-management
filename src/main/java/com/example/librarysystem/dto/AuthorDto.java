package com.example.librarysystem.dto;

public class AuthorDto {
    private Long id;
    private String name;
    private String biography;

    // Default constructor
    public AuthorDto() {}

    // All-args constructor
    public AuthorDto(Long id, String name, String biography) {
        this.id = id;
        this.name = name;
        this.biography = biography;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }
    public void setBiography(String biography) {
        this.biography = biography;
    }
}
