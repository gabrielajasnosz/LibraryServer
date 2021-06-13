package com.demo.springboot.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Specifies the entity "Category" for the JPA.
 */
@Entity
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false, updatable = false)
    private Long categoryId;

    private String title;

    public Long getCategoryId() {
        return categoryId;
    }

    public String getTitle() {
        return title;
    }
}
