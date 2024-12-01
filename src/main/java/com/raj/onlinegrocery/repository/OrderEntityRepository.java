package com.raj.onlinegrocery.repository;

import com.raj.onlinegrocery.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, Long> {

    @Query("select o.orderId from OrderEntity o join o.customer c where c.customerId = :customerId")
    List<Long> findAllOrdersByCustomerId(@Param("customerId") Long customerId);
}
