package com.demorestaurante.restaurantejava.repository;

import com.demorestaurante.restaurantejava.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
    List<OrderLine> findByOrder_Id(Long id);


}