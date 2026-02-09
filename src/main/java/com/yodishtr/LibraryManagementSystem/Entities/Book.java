package com.yodishtr.LibraryManagementSystem.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 13)
    private String isbn;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String title;

    private String publisher;

    private String category;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer publicationYear;

    private String thumbnail;

    private Integer pageCount;

    private String language;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "books", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookCopy> bookCopies = new ArrayList<>();

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
    }

    public Book(){}

    // Getters
    public Long getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public String getLanguage() {
        return language;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<BookCopy> getBookCopies(){
        return bookCopies;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    // Setters
    public void setId(Long id){
        this.id = id;
    }

    public void setIsbn(String isbn){
        this.isbn = isbn;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setPublicationYear(Integer publicationYear){
        this.publicationYear = publicationYear;
    }

    public void setPageCount(Integer pageCount){
        this.pageCount = pageCount;
    }

    public void setLanguage(String language){
        this.language = language;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

    public void setBookCopies(List<BookCopy> bookCopies){
        this.bookCopies = bookCopies;
    }

    public void setThumbnail(String thumbnail){
        this.thumbnail = thumbnail;
    }

    void addBookCopy(BookCopy bookCopy){
        bookCopies.add(bookCopy);
        bookCopy.setBook(this);
    }
}
