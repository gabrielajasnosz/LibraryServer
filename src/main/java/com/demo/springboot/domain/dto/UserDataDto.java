package com.demo.springboot.domain.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

/**
 * Klasa odpowidzialna jest za przetrzymywanie podstawowych informacji o uzytkowniku.
 */
                                                    /* lombok: */
@Getter                                             /* automatyczne generowanie getterow dla pol */
@ToString                                           /* automatyczne generowanie metody toString */
@NoArgsConstructor                                  /* automatyczne generowanie konstruktora bezparametrowego */
@FieldDefaults(level = AccessLevel.PRIVATE)         /* automatyczne generowanie zasiegu dla pol */
public class UserDataDto implements Serializable {
    String firstName;
    String lastName;
    String description;
}
