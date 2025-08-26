package com.example.librarysystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 2000)
    private String biography;
}


