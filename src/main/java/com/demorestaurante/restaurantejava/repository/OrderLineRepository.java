package com.demorestaurante.restaurantejava.repository;

import com.demorestaurante.restaurantejava.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
}