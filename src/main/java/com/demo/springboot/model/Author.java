package com.demo.springboot.model;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Specifies the entity "Author" for the JPA.
 */
@Entity
public class Author implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="author_id", nullable = false, updatable = false)
    private Long authorId;
    private String surname;
    private String name;
    private String nationality;

    @Column(name="publication_period")
    private String publicationPeriod;

    @Column(name="writing_language")
    private String writingLanguage;

    public Author() { }

    public Long getAuthorId() { return authorId;}
    public String getSurname() {return surname;}
    public String getName() { return name;}
    public String getNationality() { return nationality;}
    public String getPublicationPeriod() { return publicationPeriod;}
    public String getWritingLanguage() { return writingLanguage;}

    @Override
    public String toString() {
        return "AuthorData{" +
                "author_id='" + authorId + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", creation_period='" + publicationPeriod + '\'' +
                ", language='" + writingLanguage + '\'' +
                '}';
    }
}
