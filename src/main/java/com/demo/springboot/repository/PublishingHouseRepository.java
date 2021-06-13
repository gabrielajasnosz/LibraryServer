package com.demo.springboot.repository;

import com.demo.springboot.model.PublishingHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for Publishing House entity.
 */
@Repository
public interface PublishingHouseRepository extends JpaRepository<PublishingHouse, Long> {
}
