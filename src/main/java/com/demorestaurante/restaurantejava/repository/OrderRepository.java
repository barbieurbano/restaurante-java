package com.demorestaurante.restaurantejava.repository;

import com.demorestaurante.restaurantejava.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}