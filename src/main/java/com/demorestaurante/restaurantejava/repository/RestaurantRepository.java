package com.demorestaurante.restaurantejava.repository;

import com.demorestaurante.restaurantejava.model.Restaurant;
import com.demorestaurante.restaurantejava.model.enums.FootType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByActiveTrue();
    //Quieres que antes de entrar al detalle este activo , se tiene que pasar el parametro igualmente
    //Con el OPTIONAL ES PARA DEVOLVER 1 SOLO DATO.
    Optional<Restaurant> findByIdAndActiveTrue(Long id);

    //Haremos una QUERY para que tenga todos estos filtros
    //Tener en cuenta que el foodType puede ser null, cuando entras a la pantalla sin filtrar
    //

    @Query("""
        SELECT r from Restaurant r
        WHERE r.active = true
        AND (:footType IS NULL OR r.footType = :footType)
        AND (:price IS NULL OR r.averagePrice <= :price)
        AND (:title IS NULL OR LOWER(r.name) LIKE LOWER(CONCAT('%', :title, '%')))
        
    """)
    List<Restaurant> findActiveFiltering(
            @Param("foodType") FootType footType,
            @Param("price") Double price,
            @Param("title") String title);

    //Filtro por nombre
    //filtro por foodType
    //Filtro por precio
    //Filtro por

}