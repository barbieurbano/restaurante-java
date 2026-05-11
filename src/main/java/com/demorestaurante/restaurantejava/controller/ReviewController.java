package com.demorestaurante.restaurantejava.controller;

import com.demorestaurante.restaurantejava.model.Review;
import com.demorestaurante.restaurantejava.repository.DishRepository;
import com.demorestaurante.restaurantejava.repository.RestaurantRepository;
import com.demorestaurante.restaurantejava.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import com.demorestaurante.restaurantejava.repository.DishRepository;
import com.demorestaurante.restaurantejava.repository.RestaurantRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor // para no pedir manualmente el constructor
public class ReviewController {
    //inyectar el repositorio de reviews
    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;

    //getmapping / reviews y luego hacer el html
    @GetMapping("reviews")
    public String reviews (Model model){
        //model.addAttribute("reviews", reviewRepository.findAll());
        return "reviews/review-list";
    }
    @GetMapping("reviews/{id}")
    public String review(Model model, @PathVariable Long id){
        //model.addAttibute("review", reviewRepository.findyById(id).orElseThrow());
        return "reviews/review-detail";
    }
    //Es igual que el model pero con una redireccion
    @GetMapping("reviews/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        //reviewRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Borrado exitosamente");
        return "redirect:/reviews";
    }
    //mas adelante veremos el postmapping
}


//GetMapping reviews/new reviews-formhtml restaurant id si queremos hacer resenia de restaurante o un dishid si queremos opinar sobre un plato
@GetMapping("reviews/new")
public String newReview (Model model,
                         @RequestParam(required = false) Long restaurantId,
                         @RequestParam(required = false) Long dishId) {
    Review review = new Review();
    //agregar el restaurante o plato al que se esta haciendo la review
    //setRestaurant o setDish
    model.addAttribute("review", review);
    if(restaurantId != null){
        review.setRestaurant(restaurantRepository.findById(restaurantId).orElseThrow());
    }
    model.addAttribute("review",review);

    if(dishId != null)
        review.setDish(dishRepository.findById(dishId).orElseThrow());
    model.addAttribute("review", review);
    return "reviews/review-form";
}

//A FUTURO EL EDIT


//PostMapping reviews para save, redirect

