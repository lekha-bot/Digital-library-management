package com.example.librarysystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String resourceType;
    private String resourceUrl;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
}
