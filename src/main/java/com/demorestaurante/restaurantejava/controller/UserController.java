package com.demorestaurante.restaurantejava.controller;

import com.demorestaurante.restaurantejava.model.User;
import com.demorestaurante.restaurantejava.model.enums.Role;
import com.demorestaurante.restaurantejava.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    //navegar al formulario, necesitemos un DTO aunque podriamos enviar la entidad tal cual
    @GetMapping("admin/users/new")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        //para que el admin seleccione los roles, porque es un formulario
        model.addAttribute("roles", Role.values());
        model.addAttribute("edit",false);
        return "users/user-form";
    }

    //getmapping("admin/users/edit/{id}") user form para editar un usuario, busca el usuario que te pasan por parametro  en BD
    //no hace falta el orelse porque el propio Service lo hace
    //podriamos re utilizar esto para que el USER peuda cambiar su perfil pero con un @current para que no pueda editar otro id digamos.
    @GetMapping("admin/users/edit/{id}")
    public String editUser(Model model, @PathVariable Long id){
        //Le cambio la password para que no vaya al frontend, no se mueva, si no seria en un DTO.
        User user = userService.findById(id);
        user.setPassword(null); //no devolver esta password cifrada, se hace un DTO para cambiar las password, por temas de seguridad el editar no suele tener el cambiar passwrod es aparte.
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        model.addAttribute("edit", true);
        return "users/user-form";
    }

    //postmapping que recibe al usuario y es para guardar en BD, creacion edicion de un usuario en BD
    //postmapping /admin/users, el Redirect e spor si pone mal algo el usuario
    //Debemos detectar si es una creacion o una editacion, si debemos cargar la password antigua o crear una nueva
    @PostMapping("admin/users")
    public String save(@ModelAttribute User user, RedirectAttributes redirectAttributes){
        //creacion lo ideal es llamar al userService, creemos un metodo ahi

        try {
            if(user.getId() == null){
                userService.create(user);
                //Con redirecciones se utilizan los flash para conservar el mensaje y que no se pierda, para pasar datos , mantiene los datos ocultos en el servidor
                redirectAttributes.addFlashAttribute("message", "usuario creado");
            } else {
                //edicion
                userService.update(user);
                redirectAttributes.addFlashAttribute("message", "usuarios actualizado");
            }
            return "redirect:/admin/users";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return user.getId() == null ?
                    "redirect:/admin/users/new" : "redirect:/admin/users/edit/" + user.getId();
        }


    }

    //Faltaria el profile para que un USER pueda ver su perfil
    //Getmapping / profile
    //postmapping /profile pero LIMITADO, que no pueda cambiarse el rol, ni el email ?
}
