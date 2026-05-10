package com.demorestaurante.restaurantejava.repository;

import com.demorestaurante.restaurantejava.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByRestaurant_IdOrderByCreationDateDesc(Long id);
    List<Review> findByDish_IdOrderByCreationDateDesc(Long id);
    List<Review> findByRestaurant_IdAndRatingGreaterThanEqualOrderByCreationDateDesc(Long id, Integer rating);
}
