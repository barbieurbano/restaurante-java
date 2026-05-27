package com.demorestaurante.restaurantejava.controller;

import com.demorestaurante.restaurantejava.dto.RegisterForm;
import com.demorestaurante.restaurantejava.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//Tenemos un service que saca el usuario de la BD y nos puede servir para verificar el usuario
@Controller
@AllArgsConstructor
public class AuthController {
    private final UserService userService;
    //Con GetMapping Para el tema del login que es lo que pusimos en el secutiryCongif para indicarle a spring que te envie
    // a una pantalla html con un login
    //GetMapping / register este te tiene que llevar a la pantalla pero debemos cargar en el model un objeto
    //Primero navegamos a formulario de registro, aun no estamos recibiendo un usuairo y guardandolo en BD.
    //Se nos va a msotrar un formulario, recibiamos el Model model y con eso crear un objeto vacio dentro del model
    //Podemos poner un objeto user pero puede exponer datos que no quieres.
    //Se puede crear un DTO que es una clase que sirve para enviar datos del html al backend pero no es una entity
    //Campos que le indicas y la utilizamos para pasarle los datos de un user.
    //
    @GetMapping("register")
    public String register(Model model){
        model.addAttribute("form", new RegisterForm());
        return "auth/register";
    }
    //No necesitamos PostMapping porque spring lo hace solo

    //Necesitaremos un GetMapping /register

    //Necesitamos recibir el registro con PostMapping /register recibe un obj con usuario, password, username, email, password

    //No hace falta postMapign para login porque spring secutiry lo hace automatico,
    //Para el loguot no hace falta que tengas un @PostMapping /logout porque ya lo hace automatico sping security

    //Este es similar a lo que ya conocemos, recibes un model attribute y nos llega el usuario
    //Debe validar el formulario mirar si password es = a passwordConfirm,
    //verificar si email esta ocupado que y esto se lo podemos pasar al servicio y sea el quien se encargue de esto
    //Si esta bien creamos un user y lo guardamos en BD con save y luego lo mandas al login para que inicie sesion
    // redirectAttributes para mensaje de ok o error. Agrega un flash attribute para las redirecciones y el mensaje que le pongas


    @PostMapping("register")
    public String register(@ModelAttribute RegisterForm form, RedirectAttributes redirectAttributes){
        System.out.println(form);
        //Verificar si username ocupado
        //Verificar si email ocupado
        //Verificar password
        //Se puede poner un try catch para capturar excepciones y en el catch haces lo que quieras en caso de un error
        //Lo ideal es que los controladores se vean asi llamen a un sitio en ese sitio se haga la logica y que el
        // controlador controle a donde ir,
        try {
            userService.register(form);
            redirectAttributes.addFlashAttribute("message", "Cuenta creada correctamente, inicia sesion");
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/register";
        }


    }
    //Aun no recibe un usuairo, e sun metodo que te lelva  ala pantalla de login y ahi tendrias un formulario de usuario y password
    //Esto lo gestiona spring security
    @GetMapping("login")
    public String login(){
        return "auth/login";
    }
}
