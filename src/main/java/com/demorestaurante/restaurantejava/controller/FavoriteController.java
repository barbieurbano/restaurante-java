package com.demorestaurante.restaurantejava.controller;

import com.demorestaurante.restaurantejava.model.User;
import com.demorestaurante.restaurantejava.service.FavoriteService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class FavoriteController {
    //En vez de usar el repository de favorites, llamamos al Favorite que el usa el repository
    //El servicio te permite el testing unitario liviano, favorece el testing.
    private final FavoriteService favoriteService;

    //permite recibir un restaurante un plato y te permite guardarlo/desmarcarlo como fav
    //Pero en el user controller podriamos ver los favoitos que tenga ya un usuario.
    //toggle
    // toggle para marcar o desmarcar como favorito
    @PostMapping("favorites/toggle")
    public String toggle(
            @RequestParam String type,
            @RequestParam Long targetId,
            @RequestParam (defaultValue = "/restaurants") String redirectUrl,
            @AuthenticationPrincipal User user,
            RedirectAttributes redirectAttributes
    ){
        boolean favorited;
        if(type.equalsIgnoreCase("restaurant")){
            favorited = favoriteService.toggleRestaurant(user, targetId);
        } else if (type.equalsIgnoreCase("dish")) {
            favorited = favoriteService.toggleDish(user, targetId);
        } else {
            return "redirect:" + redirectUrl;
        }

        if(favorited){
            redirectAttributes.addFlashAttribute("message", "agregado como favorito");
        } else {
            redirectAttributes.addFlashAttribute("message", "eliminado de favorito");
        }

        return "redirect:" + redirectUrl;
    }

}
