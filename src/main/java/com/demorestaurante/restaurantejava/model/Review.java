package com.demorestaurante.restaurantejava.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

//Lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Builder.Default
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(length = 1000)
    private String description;

    private Integer rating;

    @ToString.Exclude
    @ManyToOne
    private Restaurant restaurant;

    //@ManyToOne
    //private User user;
}
