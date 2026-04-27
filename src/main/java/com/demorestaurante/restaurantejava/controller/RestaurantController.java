package com.demorestaurante.restaurantejava.controller;

import com.demorestaurante.restaurantejava.model.Dish;
import com.demorestaurante.restaurantejava.model.Restaurant;
import com.demorestaurante.restaurantejava.model.Review;
import com.demorestaurante.restaurantejava.repository.DishRepository;
import com.demorestaurante.restaurantejava.repository.RestaurantRepository;
import com.demorestaurante.restaurantejava.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor //Te ahorrar tener que actualizar toodo el tiempo el constructor
public class RestaurantController {
    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;
    private final ReviewRepository reviewRepository;

//    public RestaurantController(RestaurantRepository restaurantRepository, DishRepository dishRepository) {
//        this.restaurantRepository = restaurantRepository;
//        this.dishRepository = dishRepository;
//    }


    @GetMapping("restaurants") //Esta ruta se ve desde el navegador
    public String restaurantList(Model model) {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        model.addAttribute("restaurants", restaurants);
        model.addAttribute("numRestaurants", restaurants.size());
        model.addAttribute("title", "Lista de restaurantes");

        return "restaurants/restaurant-list"; //Esto es como se llama el HTML -> Vista (lo que se config para que ve el usuario)
    }

    @GetMapping("restaurants/{id}")
    public String restaurantDetail(@PathVariable Long id, Model model){
        //usamos el optional para evitar nullException si no tiene ese id en la BD
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);

        if(restaurantOptional.isPresent()){
           //si esxiste en BD
            Restaurant restaurant = restaurantOptional.get();
            model.addAttribute("restaurant", restaurant);

            List<Dish> platos = dishRepository.findByRestaurantIdOrderByPrice(restaurant.getId());
            model.addAttribute("dishes", platos);

            //reviews
            List<Review> reviews = reviewRepository.findByRestaurant_IdOrderByCreationDateDesc(restaurant.getId());
            model.addAttribute("reviews", reviews); // Accesibles desde HTML
            return "restaurants/restaurant-detail";
        }
        else{
            //no existe en BD
            return "redirect:/restaurants";
        }
    }

}
