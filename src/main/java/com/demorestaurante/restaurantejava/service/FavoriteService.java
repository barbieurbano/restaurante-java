package com.demorestaurante.restaurantejava.service;

import com.demorestaurante.restaurantejava.model.Favorite;
import com.demorestaurante.restaurantejava.repository.DishRepository;
import com.demorestaurante.restaurantejava.repository.FavoriteRepository;
import com.demorestaurante.restaurantejava.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//Si estuviesemos implementando un Strategy si necesitariamos un implements, por si necesitamos la interfaz.
@Service
@AllArgsConstructor
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;

    //findFavoriteRestaurants
    public List<Favorite> findFavoriteRestaurants(Long userId){
        return favoriteRepository.findByUser_IdAndRestaurantIsNotNull(userId);
    }

    public List<Favorite> findFavoriteDishes(Long userId){
        return favoriteRepository.findByUser_IdAndDishIsNotNull(userId);
    }

    //toggle restaurant
    //toggle dish

}
