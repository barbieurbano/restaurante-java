package com.demorestaurante.restaurantejava.service;

import com.demorestaurante.restaurantejava.dto.RegisterForm;
import com.demorestaurante.restaurantejava.dto.UserStatsDTO;
import com.demorestaurante.restaurantejava.model.User;
import com.demorestaurante.restaurantejava.model.enums.Role;
import com.demorestaurante.restaurantejava.repository.OrderRepository;
import com.demorestaurante.restaurantejava.repository.ReviewRepository;
import com.demorestaurante.restaurantejava.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    //Porque debemos interactuar con la bd,
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository; //Para calcular cuantas review ha hecho y cuantos pedidos ha hecho

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
// Mejor que devuelva el usuario creado para poder comprobar con testing que se crea bien public User register,
// recibes un objeto con datos y devuelves un usuario
    public User register(RegisterForm form) {
        // este haremos despues del recreo.
        //Puedes crear tu propia exception porque en funcion de un fallo u otro
        //un try catch puedes hacer muchos cachs
        if(userRepository.existsByUsername(form.getUsername()))
            throw new IllegalArgumentException("El usuario ya existe");
        if(userRepository.existsByEmail(form.getEmail()))
            throw new IllegalArgumentException("El email ya existe");
        if(! form.getPassword().equals(form.getPasswordConfirm()))
            throw new IllegalArgumentException("Las password no coinciden");

        User user = new User();
        user.setUsername(form.getUsername());
        user.setEmail(form.getEmail());
        //user.setPassword(form.getPassword()); // Esto almacena en texto sin cifrar, texto plano
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        user.setRole(Role.ROLE_USER); //Por defecto se crea en este rol.
        //Cuando se crea por primera vez se suele poner setActive en false y le envio un correo, para que lo verifique y ahi se activa la cuenta
        return userRepository.save(user);
    }

    //Metod para crear usuario admin
    //puedes hacer un findall convertir en stream meter un filtro o un map( user -> { user.setPassword(null); para ocultar la contrase al devolver el duuario return user;}).toList()
    // te ahorra el bucle for con esta lambda para que no haya problema de fuga de datos,
    // no estamos mostrando la password  en thymeleaf entonces no haria falta pero para tenerlo en cuenta

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    }

    public UserStatsDTO findStatsById(Long id){
        return new UserStatsDTO(
                reviewRepository.countByUser_Id(id),
                reviewRepository.findByUser_Id(id),
                orderRepository.countByUser_Id(id),
                orderRepository.findByUser_IdOrderByDateDesc(id),
                orderRepository.calculateTotalMoneySpentByUserId(id)
        );
    }

}



