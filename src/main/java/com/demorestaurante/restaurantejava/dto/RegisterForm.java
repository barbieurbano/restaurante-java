package com.demorestaurante.restaurantejava.dto;

import lombok.*;

//Te pone todas las anotacioens de golpe menos el builder pero el objeto nos llega creado
//Se puede crear como record  que se utilizan DTO son clases super simplificadas public record RegisterForm()
@Data
public class RegisterForm {
    //Lo que nos van a enviar exactamente en el formulario
    private String username;
    private String password;
    private String email;
    private String passwordConfirm;
    //Podriamos poner un acepta las condiciones, comunicaciones comerciales

}
