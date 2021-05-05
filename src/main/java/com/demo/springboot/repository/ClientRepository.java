package com.demo.springboot.repository;

import com.demo.springboot.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
   Boolean existsByLoginAndPassword(
            @Param("login") String login,
            @Param("password") String password
    );

    Optional<Client> findClientByLoginAndPassword(
            @Param("login") String login,
            @Param("password") String password
    );
}
