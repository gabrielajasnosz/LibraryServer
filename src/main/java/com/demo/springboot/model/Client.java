package com.demo.springboot.model;

import javax.persistence.*;
import java.io.Serializable;

/** Specifies the entity "Client" for the JPA.
 */
@Entity(name = "client")
@Table(name = "client")
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id", nullable = false, updatable = false)
    private Long clientId;

    private String login;
    private String password;
    private String name;
    private String surname;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "phone_number")
    private String phoneNumber;
    private String street;
    private String city;

    @Column(name = "zip_code")
    private String zipCode;

    public Client() {
    }

    public Long getClientId() {
        return clientId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }
}
