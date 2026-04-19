package com.demorestaurante.restaurantejava.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Detalle_Pedido")
public class OrderLine {
    @Id
    @GeneratedValue
    private Long id;
    private Integer quantity;

    @ManyToOne
    private Dish dish;
    @ManyToOne
    private Order order;

    public OrderLine(){}

    public OrderLine(Integer quantity, Dish dish, Order order) {
        this.quantity = quantity;
        this.dish = dish;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "id=" + id +
                ", quantity=" + quantity +
                '}';
    }
}
