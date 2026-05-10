package com.demorestaurante.restaurantejava.model;

import com.demorestaurante.restaurantejava.model.enums.OrderStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
// CUIDADO: order es una palabra reservada en db
// Por eso le cambiamos el nombre a la tabla
@Table(name = "Pedidos")
public class Order {
    //Atributos
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date = LocalDateTime.now();
    private Double totalPrice;
    private Integer numPeople;
    private Integer tableNumber;
    private Double tip;
    private Integer numProducts;

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getNumProducts() {
        return numProducts;
    }

    public void setNumProducts(Integer numProducts) {
        this.numProducts = numProducts;
    }

    //Enum
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    @ManyToOne
    private Restaurant restaurant;

    //Constructor

    public Order() {}

    public Order(Integer numPeople, Integer tableNumber, Double tip, Restaurant restaurant) {
        this.numPeople = numPeople;
        this.tableNumber = tableNumber;
        this.tip = tip;
        this.restaurant = restaurant;
    }

    //Getter y setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }


    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getNumPeople() {
        return numPeople;
    }

    public void setNumPeople(Integer numPeople) {
        this.numPeople = numPeople;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Double getTip() {
        return tip;
    }

    public void setTip(Double tip) {
        this.tip = tip;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", totalPrice=" + totalPrice +
                ", numPeople=" + numPeople +
                ", tableNumber=" + tableNumber +
                ", tip=" + tip +
                ", numProducts=" + numProducts +
                ", status=" + status +
                ", restaurant=" + restaurant +
                '}';
    }
}
