package com.demorestaurante.restaurantejava.controller;

import com.demorestaurante.restaurantejava.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor // para no pedir manualmente el constructor
public class ReviewController {
    //inyectar el repositorio de reviews
    //private final ReviewRepository reviewRepository;
    //getmapping / reviews y luego hacer el html
    @GetMapping("reviews")
    public String reviews (Model model){
        //model.addAttribute("reviews", reviewRepository.findAll());
        return "reviews/review-list";
    }

    //mas adelante veremos el postmapping
}
