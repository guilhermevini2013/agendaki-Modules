package com.agendaki.scheduling.configurations.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    private final RecoverTokenFilter recoverTokenFilter;

    private final String[] privateRoutes = {
            "/api/professional",
            "/api/professional/**",
            "/api/service",
            "/api/scheduling/private",
            "/api/scheduling/private/**",
    };

    private final String[] publicRoutes = {
            "/api/user/auth",
            "/api/scheduling",
            "/api/scheduling/public",
            "/api/scheduling/public/**",
            "/api/service/public",
            "/api/service/public/**",
    };

    public SecurityConfig(RecoverTokenFilter recoverTokenFilter) {
        this.recoverTokenFilter = recoverTokenFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> request
                        .requestMatchers(privateRoutes).hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/template").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/template/free").permitAll()
                        .requestMatchers(publicRoutes).permitAll()
                        .anyRequest().permitAll())
                .headers(header -> header.frameOptions(frame -> frame.disable()))
                .addFilterAt(recoverTokenFilter, UsernamePasswordAuthenticationFilter.class)
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
