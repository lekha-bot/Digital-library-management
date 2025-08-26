package com.example.librarysystem.service;

import com.example.librarysystem.entity.Author;
import com.example.librarysystem.entity.Book;
import com.example.librarysystem.entity.Resource;
import com.example.librarysystem.repository.AuthorRepository;
import com.example.librarysystem.repository.BookRepository;
import com.example.librarysystem.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    // Save book along with resources and author
    public Book saveBook(Book book) {
        // Handle author
        if (book.getAuthor() != null) {
            Long authorId = book.getAuthor().getId();
            Author author = authorRepository.findById(authorId)
                    .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + authorId));
            book.setAuthor(author);
        }

        // Handle resources
        if (book.getResources() != null) {
            book.getResources().forEach(resource -> resource.setBook(book));
        }

        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    public Book updateBook(Long id, Book bookDetails) {
        // 1️⃣ Fetch the existing book
        Book book = getBookById(id);

        // 2️⃣ Update basic fields
        book.setTitle(bookDetails.getTitle());
        book.setCategory(bookDetails.getCategory());

        // 3️⃣ Update author safely
        if (bookDetails.getAuthor() != null && bookDetails.getAuthor().getId() != null) {
            Author existingAuthor = authorRepository.findById(bookDetails.getAuthor().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Author not found with id " + bookDetails.getAuthor().getId()
                    ));
            book.setAuthor(existingAuthor);
        }

        // 4️⃣ Update resources safely (orphanRemoval friendly)
        if (bookDetails.getResources() != null) {
            // Clear existing resources
            book.getResources().clear();

            // Add new resources and set the book reference
            for (Resource resource : bookDetails.getResources()) {
                resource.setBook(book);
                book.getResources().add(resource);
            }
        }

        // 5️⃣ Save and return updated book
        return bookRepository.save(book);
    }


    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }
}
