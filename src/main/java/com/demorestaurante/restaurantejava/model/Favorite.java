package com.demorestaurante.restaurantejava.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "favorites") //Luego hacer criterio de UNICIDAD QUE UN USER NO PUEDA DARLE 2 VECES MG A UN RESTAURANT
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default//PAra que no te ponga los datos en null
    private LocalDateTime createAt = LocalDateTime.now();

    @ManyToOne
    @ToString.Exclude
    private User user;

    @ManyToOne
    @ToString.Exclude // Nosotros pondriamos la MOVIE
    private Restaurant restaurant;

    @ManyToOne
    private Dish dish;

}
