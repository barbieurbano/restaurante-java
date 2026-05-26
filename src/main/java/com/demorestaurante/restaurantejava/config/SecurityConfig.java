package com.demorestaurante.restaurantejava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    //passwordEncode es una clase que cifra las password y esto lo usa spring
    //para que spring lo detecte se pone @Bean es una manera de declarar objetos
    //Spring lo conce y lo puedes utilizar en otra parte del proyecto, es inyeccion de dependencia pero mas complicado
    //normalmente los beans se crean en config, se hace 1 sola vez.
    //Para cifrar la password
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //Rutas que hay que proteger con un securityFilterChain,para proteger acceso a rutas, alomejor estas logueado
    //pero no tienes permiso


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        //Debemos proteger rutas y luego devolvemos el objeto http
        //Que rol puede acceder a cierta ruta, que el admin pueda hacer post y el resto no
        //Te deja configurar cosas como autorized que quieres personalizar. El form login, el logout
        //Las cabeceras con headers
        //Primero las rutas permitidas luego las protegidas con un autenticathed
        //Se le puede pasar un formlogin, un logout
        //El logo de nagvar deberia ser publico

        //Para que la consola H2 funcione con Spring Security
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"));
        //H2 utiliza iframes
        http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        http.authorizeHttpRequests(
                auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()
                        //Rutas publicas tanto get como post porque te haran un login o un registro
                        .requestMatchers("/hola", "/adios", "/login","/register", "/css/**", "/images/**", "/webjars/**").permitAll()

                        //Listado y detalles publicos solo con GET y no POST (que puedas ver los restaurantes sin iniciar sesion)
                        .requestMatchers(HttpMethod.GET, "/restaurants").permitAll()
                        //Aqui le dices que debe estar autenticado para ver los detalles de un restaurante.
                        .requestMatchers(HttpMethod.GET, "/restaurants/*").permitAll()
                //Para que solamente el admin pueda crear restaurantes
                        .requestMatchers(HttpMethod.POST, "/restaurants").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/restaurants/deactivate/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/restaurants/new").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/restaurants/edit/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/restaurants/*").permitAll()

                //Ahora con los de DISHE
                        .requestMatchers(HttpMethod.GET, "/dishes").permitAll()
                        .requestMatchers(HttpMethod.POST, "dishes").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "dishes/new").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "dishes/edit/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "dishes/*").permitAll()

                //Los permisos para REVIEWS
                        .requestMatchers(HttpMethod.GET, "/reviews").permitAll()
                        .requestMatchers(HttpMethod.POST, "/reviews").authenticated()
                        .requestMatchers(HttpMethod.GET, "/reviews/new").authenticated()
                        .requestMatchers(HttpMethod.GET, "/reviews/edit/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/reviews/disable/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/reviews/delete/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/reviews/*").permitAll()

                        .requestMatchers("/orders", "/orders/**").authenticated()
                        //panel de usuarios para admins
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()

        );
        //Para configurar como se llama la pagina del login, debemos crear un metodo en el controlador
        http.formLogin(
                form -> form.loginPage("/login")
                        .defaultSuccessUrl("/restaurants", true)
                        .permitAll());
        //h2
        //logout
        return http.build();
    }
}
