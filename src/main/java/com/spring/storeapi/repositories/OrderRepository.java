package com.spring.storeapi.repositories;

import com.spring.storeapi.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
