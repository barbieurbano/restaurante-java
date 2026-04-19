package com.demorestaurante.restaurantejava.model;

import com.demorestaurante.restaurantejava.model.enums.OrderStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Pedidos")
public class Order {
    //Atributos
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private Double totalPrice;
    private Integer numPeople;
    private Integer tableNumber;
    private Double tip;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
                ", status=" + status +
                '}';
    }
}
