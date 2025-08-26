package com.example.librarysystem.service;

import com.example.librarysystem.entity.Book;
import com.example.librarysystem.entity.Borrow;
import com.example.librarysystem.entity.User;
import com.example.librarysystem.repository.BookRepository;
import com.example.librarysystem.repository.BorrowRepository;
import com.example.librarysystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    // Borrow a book
    public Borrow borrowBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (!book.isAvailable()) {
            throw new RuntimeException("Book is already borrowed");
        }

        // Mark book as unavailable
        book.setAvailable(false);
        bookRepository.save(book);

        // Create borrow record
        Borrow borrow = new Borrow();
        borrow.setUser(user);
        borrow.setBook(book);
        borrow.setBorrowDate(LocalDate.now());
        borrow.setReturnDate(null);

        return borrowRepository.save(borrow);
    }

    // Return a book
    public Borrow returnBook(Long borrowId) {
        Borrow borrow = borrowRepository.findById(borrowId)
                .orElseThrow(() -> new RuntimeException("Borrow record not found"));

        borrow.setReturnDate(LocalDate.now());

        // Mark book as available again
        Book book = borrow.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        return borrowRepository.save(borrow);
    }
    // Get all books borrowed by a user
    public List<Borrow> getBorrowsByUser(Long userId) {
        return borrowRepository.findByUserId(userId);
    }


    // List all borrow records
    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }
}
