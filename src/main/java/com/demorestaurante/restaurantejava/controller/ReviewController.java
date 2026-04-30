package com.demorestaurante.restaurantejava.controller;

import com.demorestaurante.restaurantejava.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor // para no pedir manualmente el constructor
public class ReviewController {
    //inyectar el repositorio de reviews
    private final ReviewRepository reviewRepository;
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
