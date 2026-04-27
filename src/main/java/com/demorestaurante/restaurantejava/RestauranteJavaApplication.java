package com.demorestaurante.restaurantejava;

import com.demorestaurante.restaurantejava.model.Review;
import com.demorestaurante.restaurantejava.repository.ReviewRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class RestauranteJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestauranteJavaApplication.class, args);
        //var context = SpringApplication.run(RestaurantesJavaApplication.class, args);
        //ReviewRepository reviewRepository = context.getBean(ReviewRepository.class);
        //Crear cuatro reviews de un restaurante usando Builder de lombok, es un patron de disenio que permite crear objetos
        //Los .of son con el metodo factory
        //Review r1 = Review.builder()
        //.description("Nefasto");
        //.restaurant(restaurantSpain);
        //.title("Me sirvieron la sopa sin mosca");
        //.rating(1);
        //.build();
        //reviewRepository.saveAll(List.of(review1, review2, review3));
    }
}
