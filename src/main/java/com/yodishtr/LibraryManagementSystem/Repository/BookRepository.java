package com.yodishtr.LibraryManagementSystem.Repository;

import com.yodishtr.LibraryManagementSystem.Entities.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findById(Long id);
    Optional<Book> findByIsbn(String isbn);
    Optional<Book> findByTitle(String title);
    Optional<ArrayList<Book>> findByAuthor(String author);
    Optional<ArrayList<Book>> findByPublisher(String publisher);
    Optional<ArrayList<Book>> findByTitleAndAuthor(String title, String author);
    Optional<ArrayList<Book>> findByTitleOrAuthor(String title, String author);
    Optional<ArrayList<Book>> findByTitleOrPublisher(String title, String publisher);
    Optional<ArrayList<Book>> findByTitleAndPublisher(String title, String publisher);
    Optional<ArrayList<Book>> findByAuthorAndCategory(String author, String category);

    boolean existsByIsbn(String isbn);
    boolean existsByTitle(String title);
    boolean existsByAuthor(String author);
    boolean existsByPublisher(String publisher);
    boolean existsByTitleOrAuthor(String title, String author);
    boolean existsByTitleOrPublisher(String title, String publisher);
    boolean existsByTitleAndAuthor(String title, String author);
    boolean existsByAuthorAndCategory(String author, String category);
    boolean existsByCategory(String category);

    @EntityGraph(attributePaths = {"bookCopies"})
    Optional<Book> findByIdWithCopies(Long id);

    @EntityGraph(attributePaths = {"bookCopies"})
    Optional<Book> findByTitleWithCopies(String title);

    @EntityGraph(attributePaths = {"bookCopies"})
    Optional<Book> findByIsbnWithCopies(String isbn);

    @EntityGraph(attributePaths = {"bookCopies"})
    Optional<Book> findByAuthorWithCopies(String author);
}
