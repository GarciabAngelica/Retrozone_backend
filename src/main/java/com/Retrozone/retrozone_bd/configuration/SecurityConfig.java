package com.Retrozone.retrozone_bd.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder();
    }

    // El frontend (Vite, puerto 5173) y el backend (puerto 8080) son "orígenes"
    // distintos para el navegador. Sin este bean, el navegador bloquea toda
    // petición fetch() del frontend hacia la API, aunque el backend responda
    // correctamente — el bloqueo ocurre del lado del navegador, antes de que
    // la respuesta llegue al código JS.
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        // En vez de listar cada puerto suelto (5173 de Vite dev, 5500 de Live
        // Server, 4173 de "npm run preview"...), permitimos cualquier puerto
        // en localhost/127.0.0.1. Vite además cambia de puerto solo si el que
        // pide ya está ocupado (4173 -> 4174 -> 4175...), así que una lista
        // fija se queda corta tarde o temprano.
        config.setAllowedOriginPatterns(List.of(
                "http://localhost:*", "http://127.0.0.1:*"
        ));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> {})
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .formLogin(form -> form.disable())
                .httpBasic(httpBasic -> httpBasic.disable()); // <-- Nota el punto y coma aquí

        return http.build();
    }
}
