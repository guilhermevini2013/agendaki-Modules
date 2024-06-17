package com.agendaki.financially.configurations.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final RecoverTokenFilter recoverTokenFilter;

    private final String[] privateRoutes = {
            "/api/payment",
            "/api/payment/**",
    };

    private final String[] publicRoutes = {
            "/api/pre-user",
            "/api/pre-user/auth",
    };

    public SecurityConfig(RecoverTokenFilter recoverTokenFilter) {
        this.recoverTokenFilter = recoverTokenFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(request -> request
                        .requestMatchers(privateRoutes).hasRole("PRE-USER")
                        .requestMatchers(HttpMethod.PUT, "/api/pre-user").hasRole("PRE-USER")
                        .requestMatchers(publicRoutes).permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(recoverTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authorizationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
