package com.example.librarysystem.service;

import com.example.librarysystem.dto.AuthorDto;
import com.example.librarysystem.dto.BookDto;
import com.example.librarysystem.entity.Author;
import com.example.librarysystem.entity.Book;
import com.example.librarysystem.repository.AuthorRepository;
import com.example.librarysystem.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    // CREATE using BookDto (request contains author.id)
    public BookDto createBook(BookDto request) {
        Long authorId = (request.getAuthor() != null) ? request.getAuthor().getId() : null;
        if (authorId == null) {
            throw new IllegalArgumentException("author.id is required");
        }

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Author not found with id: " + authorId));

        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setIsbn(request.getIsbn());
        book.setAuthor(author);

        Book saved = bookRepository.save(book);
        return convertToDto(saved);
    }

    // GET all
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // GET by id
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id));
        return convertToDto(book);
    }

    // convert Book entity → BookDto (NO resources)
    private BookDto convertToDto(Book book) {
        BookDto dto = new BookDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setIsbn(book.getIsbn());

        Author author = book.getAuthor();
        if (author != null) {
            AuthorDto ad = new AuthorDto();
            ad.setId(author.getId());
            ad.setName(author.getName());
            ad.setBiography(author.getBiography());
            dto.setAuthor(ad);
        }
        return dto;
    }
}
