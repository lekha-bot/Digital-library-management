package com.example.librarysystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String resourceType;
    private String resourceUrl;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonIgnoreProperties("resources")
    private Book book;
}
