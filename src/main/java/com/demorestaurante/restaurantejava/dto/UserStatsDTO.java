package com.demorestaurante.restaurantejava.dto;
//Informacion del usuario que no esta en la tabla usuario porque esta en sus relaciones/asociaciones con las demas entidades

import com.demorestaurante.restaurantejava.model.Order;
import com.demorestaurante.restaurantejava.model.Review;

import java.util.List;
//A futuro se puede agregar mas info del usuarios, por ejemplo: Favoritos, puntos de fidelizacion,..
//Esto son cosas que se calculan en base a otras tablas.
//Estos nombres son los que usaremos en THYMLEAF
public record UserStatsDTO(
        long countReviews,
        List<Review> reviews,
        long countOrders,
        List<Order> orders,
        double moneySpent
) {

}
