package com.demorestaurante.restaurantejava.repository;

import com.demorestaurante.restaurantejava.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    //Hassta que no necesitas sacarlo por el html puede que no se te ocurra que poner aqui.
    //Lo hacemos asi para separar fav de restaurantes y fav de platos, podriamos traer all pero estarian marcados.. (findByUserId para los fav de las pelicuals)
    List<Favorite> findByUser_IdAndRestaurantIsNotNull(Long id);
    List<Favorite> findByUser_IdAndDishIsNotNull(Long id);
    Optional<Favorite> findByUser_IdAndRestaurantId(Long userId, Long restaurantId);

    Optional<Favorite> findByUser_IdAndDishId(Long userId, Long dishId);

    //Falta query para ids y poder traer el corazon.
}