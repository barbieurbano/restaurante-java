package com.demorestaurante.restaurantejava.controller;

import com.demorestaurante.restaurantejava.service.FavoriteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class FavoriteController {
    //En vez de usar el repository de favorites, llamamos al Favorite que el usa el repository
    //El servicio te permite el testing unitario liviano, favorece el testing.
    private final FavoriteService favoriteService;

    //permite recibir un restaurante un plato y te permite guardarlo/desmarcarlo como fav
    //Pero en el user controller podriamos ver los favoitos que tenga ya un usuario.
    //toggle

}
