package com.demorestaurante.restaurantejava.controller;

import com.demorestaurante.restaurantejava.model.Dish;
import com.demorestaurante.restaurantejava.model.Review;
import com.demorestaurante.restaurantejava.repository.DishRepository;
import com.demorestaurante.restaurantejava.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class DishController {

    private final DishRepository dishRepository;
    private final ReviewRepository reviewRepository;


    @GetMapping("dishes/{id}")
    //Spring Framework nos da el model
    public String dishDetail(@PathVariable Long id, Model model){
        Optional<Dish> dishOptional = dishRepository.findById(id);

        if(dishOptional.isPresent()){
            Dish dish = dishOptional.get();
            model.addAttribute("dish", dish);
            //traer reviews de este plato y cagar en model
            List<Review> reviews = reviewRepository.findByDish_IdOrderByCreationDateDesc(dish.getId());
            model.addAttribute("reviews", reviews); //sin esto no podriamos acceder desde el HTML (vista)


            return "dishes/dish-detail";
        }

        return "redirect:/restaurants";
    }

}
