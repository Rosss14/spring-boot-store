package com.codewithmosh.store.orders;

import com.codewithmosh.store.users.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = "items.product")
    @Query("select o from Order o where o.customer = :customer")
    List<Order> getAllByCustomer(@Param("customer") User customer);

    @EntityGraph(attributePaths = "items.product")
    @Query("select o from Order o where o.id = :orderId")
    Optional<Order> getOrderWithItems(@Param("orderId") Long orderId);
}