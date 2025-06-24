package com.scaler.capstone.jobportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // ❌ CSRF often blocks POST/PUT in Swagger – disable only for local dev
                .csrf(csrf -> csrf.disable())

                // ✅ Public paths (Swagger + OpenAPI JSON)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/swagger-ui/**"
                        ).permitAll()
                        .anyRequest().authenticated()             // everything else → secure
                )

                // ✅ Simple HTTP-Basic auth, non-deprecated form
                .httpBasic(Customizer.withDefaults());
        //    ↳ switch to .formLogin(Customizer.withDefaults()) if you prefer a login page

        return http.build();
    }
}