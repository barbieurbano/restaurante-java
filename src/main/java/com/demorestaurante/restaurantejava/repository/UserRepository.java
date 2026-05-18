package com.demorestaurante.restaurantejava.repository;

import com.demorestaurante.restaurantejava.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//comprobar si un username o email esta pcupado para el registro. (puede ser un boolean, exists)
    //Registro: Para el registro necesitamos verificar si estan ocupados
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

    //Login: cuando hacemos login y tenemos que recuperar el user
    //Necesitamos el optional porque nos da un usuario o null
    Optional<User> findByEmail(String email);
    //Tambien optional porque maximo podria haber 1 usuario
    Optional<User> findByUsername(String username);
}