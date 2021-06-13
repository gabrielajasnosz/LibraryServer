package com.demo.springboot.repository;

import com.demo.springboot.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * JPA repository for Client entity.
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Boolean existsByLoginAndPassword(
            @Param("login") String login,
            @Param("password") String password
    );

    Boolean existsByLogin(
            @Param("login") String login
    );

    Client findClientByLoginAndPassword(
            @Param("login") String login,
            @Param("password") String password
    );

    @Modifying
    @Transactional
    @Query(
            value = "UPDATE library.client " +
                    "SET surname = '', name ='', zip_code='', city='', street='',house_number='', phone_number='', login='',password='' " +
                    "WHERE client_id=(SELECT client_id from library.client where login = :login and password=:password)", nativeQuery = true
    )
    void anonymizeClientByLoginAndPassword(
            @Param("login") String login,
            @Param("password") String password
    );
}
