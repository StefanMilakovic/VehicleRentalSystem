package org.unibl.etfbl.ip.vehiclerentalsystem.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.PrintWriter;

@Configuration
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> {
                    // Prazno - koristi WebMvcConfigurer koji si već definisao
                })
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        .requestMatchers("/api/employees/**").hasAnyRole("ADMIN","MANAGER")
                        .requestMatchers("/api/manufacturers/**").hasAnyRole("ADMIN","OPERATOR","MANAGER")
                        .requestMatchers("/api/cars/**").hasAnyRole("ADMIN","OPERATOR","MANAGER")
                        .requestMatchers("/api/electric-bicycles/**").hasAnyRole("ADMIN","OPERATOR","MANAGER")
                        .requestMatchers("/api/electric-scooters/**").hasAnyRole("ADMIN","OPERATOR","MANAGER")
                        .requestMatchers("/api/rentals/**").hasAnyRole("ADMIN","OPERATOR","MANAGER")
                        .requestMatchers("/api/vehicle-faults/**").hasAnyRole("ADMIN","OPERATOR","MANAGER")

                        .requestMatchers(HttpMethod.POST, "/api/clients").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/clients/**").hasAnyRole("ADMIN","OPERATOR","MANAGER")
                        .requestMatchers(HttpMethod.PATCH, "/api/clients/**").hasAnyRole("ADMIN", "OPERATOR", "MANAGER")
                        .requestMatchers("/api/clients/**").hasAnyRole("ADMIN","OPERATOR","MANAGER") // PATCH, DELETE itd.
                        .requestMatchers("/api/rental-prices").hasRole("MANAGER")

                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPoint()))
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
            throws Exception {
        return configuration.getAuthenticationManager();
    }


    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (HttpServletRequest request, HttpServletResponse response,
                org.springframework.security.core.AuthenticationException authException) -> {

            response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            response.setContentType("application/json");
            PrintWriter writer = response.getWriter();
            writer.println("{\"error\": \"Neautorizovan pristup: " + authException.getMessage() + "\"}");
        };
    }

}
