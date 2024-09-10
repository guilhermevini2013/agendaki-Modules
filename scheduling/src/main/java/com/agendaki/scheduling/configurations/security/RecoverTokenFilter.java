package com.agendaki.scheduling.configurations.security;


import com.agendaki.scheduling.repositories.UserRepository;
import com.agendaki.scheduling.services.token.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import java.io.IOException;

@Component
public class RecoverTokenFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final UserRepository userRepository;

    public RecoverTokenFilter(JWTService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Cookie cookie = WebUtils.getCookie(httpRequest, "jwtAdministration");
        if (cookie != null) {
            String token = cookie.getValue();
            if (token != null) {
                String subject = jwtService.verifyToken(token);
                UserRepository.UserAuthProjection userAuth = userRepository.findByEmail(subject).orElseThrow(() -> new UsernameNotFoundException("User not found"));
                UsernamePasswordAuthenticationToken authenticated = UsernamePasswordAuthenticationToken.authenticated(userAuth, null, userAuth.role);
                SecurityContextHolder.getContext().setAuthentication(authenticated);
            }
        }
        filterChain.doFilter(request, response);
    }
}
