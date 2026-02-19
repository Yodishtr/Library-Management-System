package com.yodishtr.LibraryManagementSystem.Services;

import com.yodishtr.LibraryManagementSystem.Entities.Book;
import com.yodishtr.LibraryManagementSystem.Entities.BookCopy;
import com.yodishtr.LibraryManagementSystem.Entities.User;
import com.yodishtr.LibraryManagementSystem.Repository.BookCopyRepository;
import com.yodishtr.LibraryManagementSystem.Repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookCopyRepository bookCopyRepository;

    public BookService(BookRepository bookRepository, BookCopyRepository bookCopyRepository){
        this.bookRepository = bookRepository;
        this.bookCopyRepository = bookCopyRepository;
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    @Transactional
    public boolean borrowBook(Long bookId, User user){
        Optional<Book> bookRequested = bookRepository.findById(bookId);
        if (bookRequested.isEmpty()) {
            return false;
        }
        Book actualBookRequested = bookRequested.get();
        Optional<BookCopy> bookCopyOptional = bookCopyRepository.findFirstByBookAndStatus(actualBookRequested,
                "AVAILABLE");
        if (bookCopyOptional.isEmpty()) {
            return false;
        }
        BookCopy bookCopy = bookCopyOptional.get();
        bookCopy.setCurrentUser(user);
        bookCopy.setStatus("BORROWED");
        bookCopy.setAcquisitionDate(LocalDateTime.now());
        bookCopyRepository.save(bookCopy);
        return true;
    }
}
