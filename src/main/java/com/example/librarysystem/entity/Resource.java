package com.example.librarysystem.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "resources")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // PDF, VIDEO
    private String url;  // link to resource

    // Many resources can belong to one book
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;


}

