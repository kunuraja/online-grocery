package com.raj.onlinegrocery.repository;

import com.raj.onlinegrocery.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerEntityRepository extends JpaRepository<CustomerEntity, Long> {
}
