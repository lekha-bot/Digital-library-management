package com.example.librarysystem.controller;

import com.example.librarysystem.entity.Borrow;
import com.example.librarysystem.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrows")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @PostMapping("/{userId}/{bookId}")
    public Borrow borrowBook(@PathVariable Long userId, @PathVariable Long bookId) {
        return borrowService.borrowBook(userId, bookId);
    }

    @PutMapping("/{borrowId}/return")
    public Borrow returnBook(@PathVariable Long borrowId) {
        return borrowService.returnBook(borrowId);
    }

    @GetMapping
    public List<Borrow> getAllBorrows() {
        return borrowService.getAllBorrows();
    }
    // Get all borrowed books by a specific user
    @GetMapping("/user/{userId}")
    public List<Borrow> getBorrowsByUser(@PathVariable Long userId) {
        return borrowService.getBorrowsByUser(userId);
    }

}
