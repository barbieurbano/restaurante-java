package com.demorestaurante.restaurantejava.controller;

import com.demorestaurante.restaurantejava.model.Restaurant;
import com.demorestaurante.restaurantejava.repository.RestaurantRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class RestaurantController {
    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    //@GetMapping("restaurantes") //Esta ruta se ve desde el navegador
    //public String restaurantList(Model model) {
      //  model.addAttribute("restaurantes", restaurantRepository.findAll());
        //return "restaurant-list"; //Esto es como se llama el HTML -> Vista (lo que se config para que ve el usuario)
    //}

    @GetMapping("restaurantes")
    public String restaurants(Model model){

        //Ejemplos DATOS (porque teniamos datos en testing solamente)

        model.addAttribute("restaurantes", restaurantRepository.findAll());
        model.addAttribute("saludo", "Bienvenido a la lista de restaurantes");
        return "restaurant-list";
    }

    @GetMapping("restaurantes/{id}")
    public String restaurantDetail(@PathVariable Long id, Model model){
        //usamos el optional para evitar nullException si no tiene ese id en la BD
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);

        if(restaurantOptional.isPresent()){
           //si esxiste en BD
            Restaurant restaurant = restaurantOptional.get();
            model.addAttribute("restaurant", restaurant);
            return "restaurant-detail";
        }
        else{
            //no existe en BD
            return "redirect:/restaurantes";
        }
    }
}
