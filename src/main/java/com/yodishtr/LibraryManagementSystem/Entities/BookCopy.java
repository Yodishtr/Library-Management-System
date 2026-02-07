package com.yodishtr.LibraryManagementSystem.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "book_copy")
public class BookCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "barcode", nullable = false, unique = true)
    private String barcode;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "acquisition_date")
    private LocalDateTime acquisitionDate;

    @Column(name = "condition")
    private String condition;

    @Column(name = "rental_price", nullable = false)
    private double rentalPrice;

    public BookCopy(){}

    // Getters
    public Long getId(){
        return id;
    }

    public String getBarcode(){
        return barcode;
    }

    public String getStatus(){
        return status;
    }

    public Book getBook(){
        return book;
    }

    public LocalDateTime getAcquisitionDate(){
        return acquisitionDate;
    }

    public String getCondition(){
        return condition;
    }

    public double getRentalPrice(){
        return rentalPrice;
    }

    // Setters
    public void setId(Long id){
        this.id = id;
    }

    public void setBarcode(String barcode){
        this.barcode = barcode;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setBook(Book book){
        this.book = book;
    }

    public void setAcquisitionDate(LocalDateTime acquisitionDate){
        this.acquisitionDate = acquisitionDate;
    }

    public void setCondition(String condition){
        this.condition = condition;
    }

    public void setRentalPrice(double rentalPrice){
        this.rentalPrice = rentalPrice;
    }


}
