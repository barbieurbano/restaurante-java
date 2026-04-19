package com.demorestaurante.restaurantejava.repository;

import com.demorestaurante.restaurantejava.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}