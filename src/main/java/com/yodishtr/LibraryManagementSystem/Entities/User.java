package com.yodishtr.LibraryManagementSystem.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    public enum ROLE{
        USER,
        ADMIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ROLE role;

    private String firstName;

    private String lastName;

    @OneToMany(mappedBy = "borrowed_books", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookCopy> borrowedBooks = new ArrayList<>();

    @Column(nullable = false)
    private Integer fineBalance;

    @Column(nullable = false)
    private boolean active;

    public User(){}

    public User(String username, String password, String email, ROLE role){
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getEmail(){
        return email;
    }

    public ROLE getRole(){
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<BookCopy> getBorrowedBooks() {
        return borrowedBooks;
    }

    public Integer getFineBalance() {
        return fineBalance;
    }

    public boolean isActive(){
        return active;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBorrowedBooks(List<BookCopy> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public void setFineBalance(Integer fineBalance) {
        this.fineBalance = fineBalance;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
