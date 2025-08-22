package com.example.librarysystem.entity;


import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String category;

    private boolean available = true;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;


}


