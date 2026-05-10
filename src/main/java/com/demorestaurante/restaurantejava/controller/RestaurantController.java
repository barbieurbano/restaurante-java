package com.demorestaurante.restaurantejava.controller;

import com.demorestaurante.restaurantejava.model.Dish;
import com.demorestaurante.restaurantejava.model.Restaurant;
import com.demorestaurante.restaurantejava.model.Review;
import com.demorestaurante.restaurantejava.model.enums.FoodType;
import com.demorestaurante.restaurantejava.repository.DishRepository;
import com.demorestaurante.restaurantejava.repository.RestaurantRepository;
import com.demorestaurante.restaurantejava.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


//    @GetMapping("restaurants") //Esta ruta se ve desde el navegador
//    public String restaurantList(Model model) {
//        //List<Restaurant> restaurants = restaurantRepository.findAll();
//        List<Restaurant> restaurants = restaurantRepository.findByActiveTrue();
//        model.addAttribute("restaurants", restaurants);
//        model.addAttribute("numRestaurants", restaurants.size());
//        model.addAttribute("title", "Lista de restaurantes");
//
//        return "restaurants/restaurant-list"; //Esto es como se llama el HTML -> Vista (lo que se config para que ve el usuario)
//    }

    //Estamos filtrando con el @RequestParam por tipo de comida
    @GetMapping("restaurants") //Esta ruta se ve desde el navegador
    public String restaurantList(
            Model model,
            @RequestParam(required = false) FoodType foodType,
            @RequestParam(required = false)Double price,
            @RequestParam(required = false)String title

    ) {
        //List<Restaurant> restaurants = restaurantRepository.findAll();
        List<Restaurant> restaurants = restaurantRepository.findActiveFiltering(foodType, price, title);
        model.addAttribute("restaurants", restaurants);
        model.addAttribute("numRestaurants", restaurants.size());
        model.addAttribute("title", "Lista de restaurantes");

        return "restaurants/restaurant-list"; //Esto es como se llama el HTML -> Vista (lo que se config para que ve el usuario)
    }



    @GetMapping("restaurants/{id}")
    public String restaurantDetail(@PathVariable Long id, Model model){
        //usamos el optional para evitar nullException si no tiene ese id en la BD
        Optional<Restaurant> restaurantOptional = restaurantRepository.findByIdAndActiveTrue(id);

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

    @GetMapping("restaurants/deactivate/{id}")
    public String restaurantDeactivate(@PathVariable Long id, Model model){
        //findById

        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
        //active false
        if (restaurantOptional.isPresent()){
            Restaurant restaurant = restaurantOptional.get();
            restaurant.setActive(false);
             //En el borrado de la review con flush. ver despues
            //save
            restaurantRepository.save(restaurant);
        }
        //redirect
        return "redirect:/restaurants";
    }
    //Ruta para entrar al formulario de restaurante
    @GetMapping("restaurant/new")
    public String newRestaurants(Model model){
        //agregar objeto Restaurant vacio para rellenarlo desde el formulario
        model.addAttribute("restaurant", new Restaurant());
        model.addAttribute("footTypes", FoodType.values());
        //Datos para el formulario, asociaciones  (qsi restaurante apunta a otras entity podemos sacar selectores para que seleccione)

        return "restaurants/restaurant-form";
    }

    @GetMapping("restaurants/edit/{id}")
    public String editRestaurant(@PathVariable Long id, Model model){
        model.addAttribute("restaurant", restaurantRepository.findById(id).orElseThrow());
        model.addAttribute("footType", FoodType.values());
        return "restaurants/restaurant-form";
    }

    @PostMapping("restaurants")
    public String createRestaurant(@ModelAttribute Restaurant restaurant){
        System.out.println("Restaurante recibido" + restaurant);
        //Luego veremos los log personalizados
        restaurantRepository.save(restaurant);
        return "redirect:/restaurants/" + restaurant.getId();
    }

    @GetMapping("restaurant/{id}/edit")
    public String restauranEdit(){
        return "";
    }


}
