package com.demo.springboot.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Specifies the entity "PublishingHouse" for the JPA.
 */
@Entity
public class PublishingHouse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publishing_house_id", nullable = false, updatable = false)
    private Long publishingHouseId;

    private String name;
    private String city;

    public String getName() {
        return name;
    }

    public Long getPublishingHouseId() {
        return publishingHouseId;
    }

    public String getCity() {
        return city;
    }
}
