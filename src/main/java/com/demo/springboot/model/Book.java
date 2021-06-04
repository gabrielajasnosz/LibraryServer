package com.demo.springboot.model;

import javax.persistence.*;
import java.io.Serializable;

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

    @ManyToOne()
    @JoinColumn(name = "publishing_house_id", referencedColumnName = "publishing_house_id")
    private PublishingHouse publishingHouse;

    @ManyToOne()
    @JoinColumn(name = "author_id", referencedColumnName = "author_id")
    private Author author;

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

    public PublishingHouse getPublishingHouse() {
        return publishingHouse;
    }
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

    public Author getAuthor() {
        return author;
    }
}
