package com.example.librarysystem.dto;

import java.time.LocalDate;

public class BorrowDto {
    private Long id;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    private UserSummary user;
    private BookSummary book;

    // ✅ Inner DTO for User
    public static class UserSummary {
        private Long id;
        private String username;
        private String email;

        public UserSummary(Long id, String username, String email) {
            this.id = id;
            this.username = username;
            this.email = email;
        }

        public Long getId() { return id; }
        public String getUsername() { return username; }
        public String getEmail() { return email; }
    }

    // ✅ Inner DTO for Book
    public static class BookSummary {
        private Long id;
        private String title;
        private String isbn;

        public BookSummary(Long id, String title, String isbn) {
            this.id = id;
            this.title = title;
            this.isbn = isbn;
        }

        public Long getId() { return id; }
        public String getTitle() { return title; }
        public String getIsbn() { return isbn; }
    }

    public BorrowDto(Long id, LocalDate borrowDate, LocalDate returnDate, UserSummary user, BookSummary book) {
        this.id = id;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.user = user;
        this.book = book;
    }

    public Long getId() { return id; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public UserSummary getUser() { return user; }
    public BookSummary getBook() { return book; }
}
