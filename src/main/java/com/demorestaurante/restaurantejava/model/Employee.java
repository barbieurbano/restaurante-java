package com.demorestaurante.restaurantejava.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Empleados")
public class Employee {
    //Atributos
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String dni;

    //Asociaciones
    @ManyToOne
    private Restaurant restaurant;

    //Getter y Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fisrtName) {
        this.firstName = fisrtName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    //Constructores
    public Employee(){}

    public Employee(String firstName, String lastName, Integer age, String dni) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.dni = dni;
    }

    // SI EL RESTAURANTE ES NULL ENTONCES HACE NULL.getId() Y DA ERROR NULL POINTER EXCEPTION
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", dni='" + dni + '\'' +
                ", restaurant=" + (restaurant != null ? restaurant.getId() : null) + '}';
    }
}
