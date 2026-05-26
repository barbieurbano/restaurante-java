package com.demorestaurante.restaurantejava.controller;

import com.demorestaurante.restaurantejava.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//Rest controller devuelve JSON, es un objeto java que se convierte en json para separar el frontend
//La seguridad crea un token que viaja en las cabeceras de las peticiones y se debe configurar aparte.

@AllArgsConstructor
@Controller

public class UserController {
//user lsit, entrar aqui seria lo mas facil porque con un findall
    //que quieres ir a un usuario en particular entras por id al user-detail
    //Esto lo veria solo el admin
    private UserService userService; // Que el service llame al repository

    //user list, debemos cargar los usuarios por eso necesitamos model, vamos sin filtros
    @GetMapping("admin/users")
    public String list(Model model){
        model.addAttribute("users", userService.findAll());
        return "users/user-list";
    }

    // user detail con un findId que te devuelva un optional en el service, necesitamos ademas del model el pathvariable
    @GetMapping("admin/users/{id}")
    public String detail(Model model, @PathVariable long id){
        model.addAttribute("user", userService.findById(id)); //Carga un objeto USER
        model.addAttribute("userStats", userService.findStatsById(id));//Carga un nuevo objeto (userStatDTO en dto), UserStatsDTO. es un nuevo archivo que tiene los campos que necesitas y puedne venir de varios repositorios, calculos agrgados como suma del precio
        return "users/user-detail";
    }


    //user form para editar un usuario

}
