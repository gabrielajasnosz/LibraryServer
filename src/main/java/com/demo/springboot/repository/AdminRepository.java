package com.demo.springboot.repository;

import com.demo.springboot.model.Admin;
import com.demo.springboot.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Boolean existsByLoginAndPassword(
            @Param("login") String login,
            @Param("password") String password
    );

    Admin findAdminByLoginAndPassword(
            @Param("login") String login,
            @Param("password") String password
    );
}
