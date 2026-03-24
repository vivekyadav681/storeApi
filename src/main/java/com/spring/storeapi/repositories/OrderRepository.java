package com.spring.storeapi.repositories;

import com.spring.storeapi.entities.Order;
import com.spring.storeapi.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = "items.product")
    @Query("SELECT o FROM Order o WHERE o.customer = :customer")
    List<Order> getAllByCustomer(@Param("customer") User user);

    @EntityGraph(attributePaths = "items.product")
    @Query("select o from Order o where o.id = :orderId")
    Optional<Order> getOrderWithItems(@Param("orderId") Long orderId);
}
