package com.magentamause.drinkmanagerbackend.repository;

import com.magentamause.drinkmanagerbackend.entity.Order;
import com.magentamause.drinkmanagerbackend.entity.OrderStatus;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByPublicId(String publicId);

    /** Number of orders in the given status created at or before the given instant (queue position). */
    long countByStatusAndCreatedAtLessThanEqual(OrderStatus status, Instant createdAt);

    List<Order> findAllByStatusOrderByCreatedAtAsc(OrderStatus status);

    List<Order> findAllByOrderByCreatedAtDesc();
}
