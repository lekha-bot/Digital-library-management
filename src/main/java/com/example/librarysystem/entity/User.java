package com.example.librarysystem.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "users") // 'user' is reserved keyword in PostgreSQL
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username; // login name

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role; // STUDENT, ADMIN, LIBRARIAN etc.

    // A user can borrow many books
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Borrow> borrowRecords;

    // Getters and Setters
}

