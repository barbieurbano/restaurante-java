package com.demorestaurante.restaurantejava.repository;

import com.demorestaurante.restaurantejava.model.Dish;
import com.demorestaurante.restaurantejava.model.Review;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByRestaurant_IdOrderByCreationDateDesc(Long id);
    List<Review> findByDish_IdOrderByCreationDateDesc(Long id);
    List<Review> findByRestaurant_IdAndRatingGreaterThanEqualOrderByCreationDateDesc(Long id, Integer rating);

    Optional<Dish> findByDish_Id(Long id);
    long countByUser_Id(Long id);
    List<Review> findByUser_Id(Long id);
}
