package com.demo.springboot.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class RentalHistory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id", nullable = false, updatable = false)
    private Long rentalId;
    private String rentalDate;
    private String returnDate;

    @ManyToOne()
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Book book;

    @ManyToOne()
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    private Client client;


    public RentalHistory() {
    }

    public RentalHistory(String rentalDate, String returnDate, Book book, Client client) {
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.book = book;
        this.client = client;
    }


    public Long getRentalId() {
        return rentalId;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public Book getBook() {
        return book;
    }

    public Client getClient() {
        return client;
    }

}
