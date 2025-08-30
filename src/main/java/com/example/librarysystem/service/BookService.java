package com.example.librarysystem.service;

import com.example.librarysystem.entity.Book;
import com.example.librarysystem.entity.Resource;
import com.example.librarysystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // ✅ Create / Save book (with resources)
    public Book saveBook(Book book) {
        if (book.getResources() != null) {
            for (Resource resource : book.getResources()) {
                resource.setBook(book); // link resource to book
            }
        }
        return bookRepository.save(book);
    }

    // ✅ Get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // ✅ Get book by ID
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    // ✅ Update book by ID
    public Optional<Book> updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id).map(existingBook -> {
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setIsbn(updatedBook.getIsbn());
            existingBook.setAuthor(updatedBook.getAuthor());

            if (updatedBook.getResources() != null) {
                for (Resource resource : updatedBook.getResources()) {
                    resource.setBook(existingBook); // link new resources
                }
                existingBook.setResources(updatedBook.getResources());
            }

            return bookRepository.save(existingBook);
        });
    }

    // ✅ Delete book by ID
    public boolean deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
