package com.raj.onlinegrocery.repository;

import com.raj.onlinegrocery.entity.GroceryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryEntityRepository extends JpaRepository<GroceryEntity, Long> {
}
