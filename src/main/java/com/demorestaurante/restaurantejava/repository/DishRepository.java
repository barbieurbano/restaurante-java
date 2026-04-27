package com.demorestaurante.restaurantejava.repository;

import com.demorestaurante.restaurantejava.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findByPriceLessThanEqual(Double price);
    List<Dish> findByRestaurantIdOrderByPrice(Long id);

}