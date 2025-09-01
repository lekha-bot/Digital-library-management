package com.example.librarysystem.controller;

import com.example.librarysystem.dto.BorrowDto;
import com.example.librarysystem.entity.Borrow;
import com.example.librarysystem.entity.Book;
import com.example.librarysystem.entity.User;
import com.example.librarysystem.repository.BorrowRepository;
import com.example.librarysystem.repository.BookRepository;
import com.example.librarysystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/borrows")
public class BorrowController {

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    // ✅ POST - Create a borrow record
    @PostMapping
    public BorrowDto createBorrow(@RequestBody Borrow borrowRequest) {
        // Find User
        User user = userRepository.findById(borrowRequest.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Find Book
        Book book = bookRepository.findById(borrowRequest.getBook().getId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Set references
        borrowRequest.setUser(user);
        borrowRequest.setBook(book);

        // Save borrow
        Borrow saved = borrowRepository.save(borrowRequest);

        return mapToDto(saved);
    }

    // ✅ GET - List all borrows
    @GetMapping
    public List<BorrowDto> getAllBorrows() {
        List<Borrow> borrows = borrowRepository.findAll();
        return borrows.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ✅ Convert Borrow → BorrowDto
    private BorrowDto mapToDto(Borrow borrow) {
        return new BorrowDto(
                borrow.getId(),
                borrow.getBorrowDate(),
                borrow.getReturnDate(),
                new BorrowDto.UserSummary(
                        borrow.getUser().getId(),
                        borrow.getUser().getUsername(),
                        borrow.getUser().getEmail()
                ),
                new BorrowDto.BookSummary(
                        borrow.getBook().getId(),
                        borrow.getBook().getTitle(),
                        borrow.getBook().getIsbn()
                )
        );
    }
}
