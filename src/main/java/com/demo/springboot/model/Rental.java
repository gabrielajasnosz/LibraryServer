package com.demo.springboot.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Specifies the entity "Rental" for the JPA.
 */
@Entity
public class Rental implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id", nullable = false, updatable = false)
    private Long rentalId;
    private String rentalDate;
    private String returnDate;

    /**
     * Joins entity Book with the Rental column.
     */
    @ManyToOne()
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Book book;

    /**
     * Joins entity Client with the Rental column.
     */
    @ManyToOne()
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    private Client client;


    public Rental() {
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

    /**
     * Gets the book of the rental.
     *
     * @return A book entity  representing the rental's book.
     */
    public Book getBook() {
        return book;
    }

    /**
     * Gets the client of the rental.
     *
     * @return A Client entity representing the rental's Client.
     */
    public Client getClient() {
        return client;
    }

}
