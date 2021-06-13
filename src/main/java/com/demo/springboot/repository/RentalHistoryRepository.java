package com.demo.springboot.repository;

import com.demo.springboot.model.RentalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for Rental History entity.
 */
@Repository
public interface RentalHistoryRepository extends JpaRepository<RentalHistory, Long> {
}
