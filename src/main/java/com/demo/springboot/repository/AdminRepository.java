package com.demo.springboot.repository;

import com.demo.springboot.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Boolean existsByLoginAndPassword(
            @Param("login") String login,
            @Param("password") String password
    );
}
