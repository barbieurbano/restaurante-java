package com.demorestaurante.restaurantejava.model;

import com.demorestaurante.restaurantejava.model.enums.DishType;
import jakarta.persistence.*;

@Entity
@Table(name = "Platos")
public class Dish {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private Double price;

    @ManyToOne
    private Restaurant restaurant;

    @Enumerated(EnumType.STRING)
    private DishType dishType;

    public Dish() {}

    public Dish(Long id, String name, String description, Double price, Restaurant restaurant, DishType dishType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.restaurant = restaurant;
        this.dishType = dishType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public DishType getDishType() {
        return dishType;
    }

    public void setDishType(DishType dishType) {
        this.dishType = dishType;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", restaurant=" + restaurant +
                ", dishType=" + dishType +
                '}';
    }
}
