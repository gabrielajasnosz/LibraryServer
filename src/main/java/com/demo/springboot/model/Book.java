package com.demo.springboot.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Specifies the entity "Book" for the JPA.
 */
@Entity
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", nullable = false, updatable = false)
    private Long bookId;

    private String title;

    @Column(name = "book_language")
    private String bookLanguage;

    @Column(name = "publication_date")
    private String publicationDate;

    private Boolean availability;
    private String description;

    /**
     * Joins entity PublishingHouse with the Book column.
     */
    @ManyToOne()
    @JoinColumn(name = "publishing_house_id", referencedColumnName = "publishing_house_id")
    private PublishingHouse publishingHouse;

    /**
     * Joins entity Author with the Book column.
     */
    @ManyToOne()
    @JoinColumn(name = "author_id", referencedColumnName = "author_id")
    private Author author;

    /**
     * Joins entity Category with the Book column.
     */
    @ManyToOne()
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    public Book() {
    }

    public Long getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    /** Gets the publishing house of the book.
     * @return A PublishingHouse entity representing the book's PublishingHouse
     */
    public PublishingHouse getPublishingHouse() {
        return publishingHouse;
    }

    /** Gets the category of the book.
     * @return A Category entity representing the book's Category.
     */
    public Category getCategory() {
        return category;
    }

    public String getBookLanguage() {
        return bookLanguage;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public String getDescription() {
        return description;
    }

    /** Gets the Author of the book.
     * @return An Author entity representing the book's Author.
     */
    public Author getAuthor() {
        return author;
    }
}
