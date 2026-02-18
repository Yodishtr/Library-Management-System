package com.yodishtr.LibraryManagementSystem.Services;

import com.yodishtr.LibraryManagementSystem.Entities.Book;
import com.yodishtr.LibraryManagementSystem.Entities.BookCopy;
import com.yodishtr.LibraryManagementSystem.Repository.BookCopyRepository;
import com.yodishtr.LibraryManagementSystem.Repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class BookPopulationService implements CommandLineRunner {
    private static final int DEFAULT_COPIES_PER_BOOK = 3;

    private final BookRepository bookRepository;
    private final BookCopyRepository bookCopyRepository;
    private final BookAPIService bookAPIService;

    public BookPopulationService(BookRepository bookRepository, BookCopyRepository bookCopyRepository,
                                 BookAPIService bookAPIService){
        this.bookRepository = bookRepository;
        this.bookCopyRepository = bookCopyRepository;
        this.bookAPIService = bookAPIService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception{
        System.out.println("Populating book db");
        ClassPathResource isbnTxtFile = new ClassPathResource("isbns.txt");
        FileReader isbnReader = new FileReader(isbnTxtFile.getFile());
        BufferedReader isbnBufferedReader = new BufferedReader(isbnReader);
        ArrayList<String> isbnList = new ArrayList<>();
        try{
            String isbnLine = isbnBufferedReader.readLine();
            while (isbnLine != null){
                isbnList.add(isbnLine.trim());
                isbnLine = isbnBufferedReader.readLine();
            }
        } catch (IOException io){
            io.printStackTrace();
        } finally{
            isbnBufferedReader.close();
        }
        for (String isbn : isbnList){
            Optional<Book> currBook = bookRepository.findByIsbn(isbn);
            if (currBook.isEmpty()){
                Optional<Book> apiBook = bookAPIService.getBookByIsbn(isbn);
                if (apiBook.isPresent()){
                    Book savedBook = bookRepository.save(apiBook.get());
                    ArrayList<BookCopy> bookCopyList = new ArrayList<>();
                    int i = 0;
                    while (i < DEFAULT_COPIES_PER_BOOK){
                        BookCopy bookCopy = new BookCopy();
                        bookCopy.setBarcode(isbn + "-" + String.valueOf(i));
                        bookCopy.setStatus("AVAILABLE");
                        bookCopy.setCurrentUser(null);
                        bookCopy.setBook(savedBook);
                        bookCopy.setRentalPrice(0.0);
                        bookCopy.setAcquisitionDate(LocalDateTime.now());
                        bookCopy.setCondition("NEW");
                        bookCopyList.add(bookCopy);
                        i++;
                    }
                    bookCopyRepository.saveAll(bookCopyList);
                }
            }
        }
    }
}
