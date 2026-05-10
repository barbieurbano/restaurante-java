package com.demorestaurante.restaurantejava.controller;

import com.demorestaurante.restaurantejava.model.Dish;
import com.demorestaurante.restaurantejava.model.Review;
import com.demorestaurante.restaurantejava.model.enums.DishType;
import com.demorestaurante.restaurantejava.repository.DishRepository;
import com.demorestaurante.restaurantejava.repository.RestaurantRepository;
import com.demorestaurante.restaurantejava.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class DishController {

    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;
    //private final ReviewRepository reviewRepository;

    @GetMapping("dishes")
    public String listDishes(Model model){
        List<Dish> dishes = dishRepository.findAll();
        model.addAttribute("dishes", dishes);
        return "dishes/dish-list";
    }


    @GetMapping("dishes/{id}")
    //Spring Framework nos da el model
    public String dishDetail(@PathVariable Long id, Model model){
        Optional<Dish> dishOptional = dishRepository.findById(id);

        if(dishOptional.isPresent()){
            Dish dish = dishOptional.get();
            model.addAttribute("dish", dish);
            //traer reviews de este plato y cagar en model
            //List<Review> reviews = reviewRepository.findByDish_IdOrderByCreationDateDesc(dish.getId());
            //model.addAttribute("reviews", reviews); //sin esto no podriamos acceder desde el HTML (vista)


            return "dishes/dish-detail";
        }

        return "redirect:/restaurants";
    }

    //GET newDish -- Aqui es donde debemos pensar que queremos cargar en el formulario.
    @GetMapping("dishes/new")
    public String newDish(Model model){
        model.addAttribute("dish", new Dish());
        //Para que pueda seleccionar uno de los enums, datos que necesito en el formulario.
        model.addAttribute("dishTypes", DishType.values());
       //Para que pueda seleccionar el restaurante es al que pertenece el plato
        model.addAttribute("restaurants", restaurantRepository.findAll());

        return "dishes/dish-form";
    }

    //GET editDish, se pone el orElse .. porque devuelve un optional y para no tener problema con null
    @GetMapping("dishes/edit/{id}")
    public String editDish(@PathVariable Long id, Model model){
        model.addAttribute("dish", dishRepository.findById(id).orElseThrow());
        model.addAttribute("dishTypes", DishType.values());
        model.addAttribute("restaurants", restaurantRepository.findAll());

        return "dishes/dish-form";
    }

    //POST saveDish
    @PostMapping("dishes")
    public String saveDish(@ModelAttribute Dish dish){
        dishRepository.save(dish);
        return "redirect:/dishes";
    }

}
