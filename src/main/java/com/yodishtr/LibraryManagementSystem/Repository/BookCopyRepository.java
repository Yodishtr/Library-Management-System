package com.yodishtr.LibraryManagementSystem.Repository;

import com.yodishtr.LibraryManagementSystem.Entities.Book;
import com.yodishtr.LibraryManagementSystem.Entities.BookCopy;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {

    Optional<BookCopy> findBookCopyById(Long id);
    //Optional<BookCopy> findBookCopyByTitle(String title);
    Optional<BookCopy> findByBarcode(String barcode);
    Optional<BookCopy> findFirstByBookAndStatus(Book book, String status);

    @EntityGraph(attributePaths = {"book"})
    Optional<BookCopy> findBookCopyWithBookById(Long id);

    //@EntityGraph(attributePaths = {"book"})
    //Optional<BookCopy> findBookCopyWithBookByTitle(String title);

    @EntityGraph(attributePaths = {"book"})
    Optional<BookCopy> findBookCopyByBarcode(String barcode);

}
