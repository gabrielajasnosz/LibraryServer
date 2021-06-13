package com.demo.springboot.repository;

import com.demo.springboot.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for Category entity.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
