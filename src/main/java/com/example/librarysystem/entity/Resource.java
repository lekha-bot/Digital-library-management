package com.example.librarysystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "resource")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // PDF, VIDEO
    private String url;  // link to resource

    @ManyToOne
    @JoinColumn(name = "book_id" , nullable = false)
    @JsonBackReference   // 👈 avoids loop
    private Book book;


}
