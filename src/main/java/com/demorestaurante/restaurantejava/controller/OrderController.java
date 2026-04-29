package com.demorestaurante.restaurantejava.controller;

import com.demorestaurante.restaurantejava.repository.OrderLineRepository;
import com.demorestaurante.restaurantejava.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class OrderController {
    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;

    //Filtros en la url y en base del parametro que te pasan hacer el filtro
    //Filtrar por restaurante, filtrar por usuario (necesitamos el usuario para eso)

    //@GetMapping orders/{id} estamos filtramos por id vas por un pedido en concreto.
    //Ya tenemos pedido no estamos sacandolo por pantalla
    //PostMapping te llega un objeto y puedes crearlo

    @GetMapping("order")
    public String orders(Model model){
        model.addAttribute("orders", orderRepository.findAll());
        return "orders/order-list";
    }

    @GetMapping("orders/{id}")
    public String order(Model model, @PathVariable Long id){
        //si el pedido no existe te lanza una excepcion
        model.addAttribute("order", orderRepository.findById(id).orElseThrow());
        model.addAttribute("orderLines", orderLineRepository.findByOrder_Id(id));
        return "orders/order-detail";
    }
}
