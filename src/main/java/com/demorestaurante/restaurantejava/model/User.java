package com.demorestaurante.restaurantejava.model;

import com.demorestaurante.restaurantejava.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

//para que sea capaz de inyectar el usuario y te va a pedir que crees
// unos metodos en esta clase, saber si un usuario esta autenticado,
// si la cuenta esta bloqueada (implements UserDetails).
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Logramos que sean unicos y obligatorios, siempre debes tener un email
    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    //Es el unico que dejamos. Por los roles, devuelve una coleccion de roles. Nosotros le decimos que nos de 1 solamente.
    //Devuelve los metodos de role.
    //Entender si un usuario tiene permisos o no.. Cuando quieras acceder a la URL directo igualmente comprueba si tiene permisos.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

}
