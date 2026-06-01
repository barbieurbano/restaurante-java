package com.demorestaurante.restaurantejava.service;

import com.demorestaurante.restaurantejava.model.Favorite;
import com.demorestaurante.restaurantejava.model.Restaurant;
import com.demorestaurante.restaurantejava.model.User;
import com.demorestaurante.restaurantejava.repository.DishRepository;
import com.demorestaurante.restaurantejava.repository.FavoriteRepository;
import com.demorestaurante.restaurantejava.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    //Para que se pueda marcar/quitar un restaurante, primero que busque si esta marcado como fav y si ya esta que lo borre y si no lo esta que lo agregue
    public boolean toggleRestaurant(User user, Long restaurantId){
        //buscar si este user tiene este restrautant ya marcado como FAV


        Optional<Favorite> existing = favoriteRepository.findByUser_IdAndRestaurantId(user.getId(), restaurantId);

        //Si ya lo tiene, lo borramos de fav
        if(existing.isPresent()){
            favoriteRepository.delete(existing.get());
            return false;
        }
        //Si no lo tiene, lo creamos y guardamos como fav
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow();
        favoriteRepository.save(Favorite.builder().restaurant(restaurant).user(user).build());
        return true;
    }

    public boolean toggleDish(User user, Long dishId){
        Optional<Favorite> existing = favoriteRepository.findByUser_IdAndDishId(user.getId(), dishId);

        //Si ya lo tiene, lo borramos de fav
        if(existing.isPresent()){
            favoriteRepository.delete(existing.get());
            return false;
        }
        //Si no lo tiene, lo creamos y guardamos como fav
        Restaurant restaurant = restaurantRepository.findById(dishId).orElseThrow();
        favoriteRepository.save(Favorite.builder().restaurant(restaurant).user(user).build());
        return true;
    }

}
