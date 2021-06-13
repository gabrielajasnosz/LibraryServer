package com.demo.springboot.repository;

import com.demo.springboot.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JPA repository for Rental entity.
 */
@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    @Query(
            value = "select * from library.rental natural join library.book where" +
                    " client_id=(SELECT client_id from library.client where login = :login and password=:password)", nativeQuery = true

    )
    List<Rental> getRentalByClientLoginAndPassword(
            @Param("login") String login,
            @Param("password") String password
    );


    @Query(
            value = "INSERT INTO library.rental (book_id, client_id, rental_date, return_date)" +
                    "VALUES(:bookId, (SELECT client_id from library.client where login = :login and password = :password), current_date, current_date+30) RETURNING rental_id, book_id, client_id, rental_date, return_date ", nativeQuery = true
    )
    Rental rentBook(@Param("login") String login,
                    @Param("password") String password,
                    @Param("bookId") Long bookId
    );

    List<Rental> deleteRentalByRentalId(@Param("rentalId") Long rentalId);

    Rental findRentalByRentalId(Long rentalId);


}
