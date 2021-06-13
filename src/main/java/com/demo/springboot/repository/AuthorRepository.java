package com.demo.springboot.repository;

import com.demo.springboot.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * JPA repository for Author entity.
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
