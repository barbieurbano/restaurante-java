package com.demorestaurante.restaurantejava.repository;

import com.demorestaurante.restaurantejava.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

    List<OrderLine> findByOrder_Id(Long id);
    Optional<OrderLine> findByOrder_IdAndDish_Id(Long orderId, Long dishId);

    @Query("""
    SELECT SUM(ol.quantity * ol.dish.price)
    FROM OrderLine ol WHERE ol.order.id = ?1
    """)
    Double calculateTotalPrice(Long orderId);

}