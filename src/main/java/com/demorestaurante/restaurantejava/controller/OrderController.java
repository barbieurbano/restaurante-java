package com.demorestaurante.restaurantejava.controller;

import com.demorestaurante.restaurantejava.model.Dish;
import com.demorestaurante.restaurantejava.model.Order;
import com.demorestaurante.restaurantejava.model.OrderLine;
import com.demorestaurante.restaurantejava.model.Restaurant;
import com.demorestaurante.restaurantejava.model.enums.OrderStatus;
import com.demorestaurante.restaurantejava.repository.DishRepository;
import com.demorestaurante.restaurantejava.repository.OrderLineRepository;
import com.demorestaurante.restaurantejava.repository.OrderRepository;
import com.demorestaurante.restaurantejava.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class OrderController {
    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;
    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;

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
        Order order = orderRepository.findById(id).orElseThrow();

        //si el pedido no existe te lanza una excepcion
        model.addAttribute("order", order);
        model.addAttribute("orderLines", orderLineRepository.findByOrder_Id(id));

        //Cargar los platos dishes porque en lla pantalla del pedido necesitamos los que necestiamos y filtrando por restaurante
        //Filtrar por RESTAURANT
        //Cargar platos para que el usuario pueda seleccionarlos para el pedido
        List<Dish> dishes = dishRepository.findByRestaurantIdOrderByPrice(order.getRestaurant().getId());
        model.addAttribute("dishes", dishes);

        return "orders/order-detail";
    }

    //Necesitamos un metodo @GetMapping / orders/new?restaurantId=1 , cambiar review por order y deberia funcionar porque lo hicimos ayer.
    //Enlazar desde restaurant detail.
    //Necesitamos un metodo en el repository que necesite traer un pedido a partir de un restaurante.
    //Como si o si me tiene que pasar un restaurante no va el @RequestParam( request=false) en
    //Debe cargar un restaurante y un nuevo order
    @GetMapping("orders/new")
    public String newOrder(Model model, @RequestParam Long restaurantId){
        Restaurant restaurant  = restaurantRepository.findById(restaurantId).orElseThrow();
        Order order = new Order();
        order.setRestaurant(restaurant);
        model.addAttribute("order", order);
        return"orders/order-form";
    }


    //Necesitamos un @PostMapping / orders
    //cuando se crea el pedido por primera vez, para ifnalizar el pedido seria otro metodo con el fisnish que cerraria el pedido.
    //Para CREAR EL PEDIDO
    @PostMapping("orders")
    public String newOrder(@ModelAttribute Order order){
        order.setStatus(OrderStatus.PENDING);
        order.setDate(LocalDateTime.now());
        order.setTotalPrice(0d);
        orderRepository.save(order);
        //Se enviar al detalle del pedido que se acaba de crear.
        return "redirect:/orders" + order.getId();
    }


    //Lo de agregar lineas de pedidos seria NUEVO
    // @PostMapping("orders/{id}/lines") dishId=1 , debemos tocar el order detail para mostrar los precios de estos.
    //para CREAR LINEAS DE PEDIDO
    @PostMapping("orders/{id}/lines")
    public String addLineDish(@PathVariable Long id, @RequestParam Long dishId){
        Order order = orderRepository.findById(id).orElseThrow();
        Dish dish = dishRepository.findById(dishId).orElseThrow();

        Optional<OrderLine> lineOptional = orderLineRepository.findByOrder_IdAndDish_Id(id, dishId);

        OrderLine orderLine;
        if(lineOptional.isPresent()){
            orderLine = lineOptional.get();
            orderLine.setQuantity(orderLine.getQuantity() + 1);
        } else{
            orderLine = new OrderLine();
            orderLine.setDish(dish);
            orderLine.setOrder(order);
            orderLine.setQuantity(1);
        }
        orderLineRepository.save(orderLine);

        //Programacion estilo funcional
//        OrderLine line = orderLineRepository
//                .findByOrder_IdAndDish_Id(id, dishId)
//                .orElseGet(() -> new OrderLine(0, order, dish));
//        line.setQuantity(line.getQuantity() + 1);
//        orderLineRepository.save(line);
        if(order.getStatus() == OrderStatus.PENDING)
            order.setStatus(OrderStatus.IN_PROGRESS);
        Double totalprice = orderLineRepository.calculateTotalPrice(order.getId());
        order.setTotalPrice(totalprice);

        orderRepository.save(order);
        return "redirect:/orders/";
    }

    //Fase de finalizar pedido, cambiar estatus. findById order, set status, recalcular (por si se agrega descuento, chupito, postre).
    @GetMapping("orders/{id/finish}")
    public String finish(@PathVariable Long id){
        Order order = orderRepository.findById(id).orElseThrow();
        order.setStatus(OrderStatus.FINISHED);
        //ahora deberia recalcular el precio
        orderLineRepository.calculateTotalPrice(order.getId());
        orderRepository.save(order);
        return "redirect:/orders" + id;
    }


}
