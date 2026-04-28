package com.demorestaurante.restaurantejava.repository;

import com.demorestaurante.restaurantejava.model.Review;

import java.util.List;

public interface ReviewRepository {
    List<Review> findByRestaurant_IdOrderByCreationDateDesc(Long id);
    List<Review> findByDish_IdOrderByCreationDateDesc(Long id);
}
