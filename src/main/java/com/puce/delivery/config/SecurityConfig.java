package com.puce.delivery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Desactiva la protección CSRF
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/**").permitAll()  // Permite acceso a todas las rutas
                .anyRequest().authenticated()  // Requiere autenticación para cualquier otra solicitud
            );

        return http.build();
    }
}
