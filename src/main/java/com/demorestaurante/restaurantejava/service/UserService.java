package com.demorestaurante.restaurantejava.service;

import com.demorestaurante.restaurantejava.model.User;
import com.demorestaurante.restaurantejava.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    //Porque debemos interactuar con la bd,
    private final UserRepository userRepository;

    //meto para buscar el usuario BD por su username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Opcion tradicional:
        Optional<User> user = userRepository.findByUsername(username);
        //Deberiamos hacerlo con el orElseThrows

        if (user.isPresent()) {
            return user.get();
        } else {
            //Devuelve una excepcion
            throw new UsernameNotFoundException("Usuario no encontrado con username: " + username);
        }
        //Opcion de programacion funcional
//        return userRepository.findByUsername(username).
//                orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado con username: " + username));

    }
}

