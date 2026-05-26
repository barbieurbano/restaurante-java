package com.demorestaurante.restaurantejava.repository;

import com.demorestaurante.restaurantejava.model.Order;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser_IdOrderByDateDesc(Long id);
    //Esto solamente cuenta cuantos pedidos has hecho no cuanto te has gastado.
    long countByUser_Id(Long id);

    @Query("""
    SELECT COALESCE(sum(o.totalPrice),0.0) FROM Order o WHERE o.user.id = :userId
    """)
    double calculateTotalMoneySpentByUserId(Long userId);
}