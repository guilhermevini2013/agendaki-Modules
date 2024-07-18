package com.agendaki.scheduling.configurations.security;


import com.agendaki.scheduling.models.user.User;
import com.agendaki.scheduling.repositories.UserRepository;
import com.agendaki.scheduling.services.token.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RecoverTokenFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final UserRepository userRepository;

    public RecoverTokenFilter(final JWTService jwtService, final UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);
        if (token != null) {
            String subject = jwtService.verifyToken(token);
            User userAuth = userRepository.findByEmail(subject).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            UsernamePasswordAuthenticationToken authenticated = UsernamePasswordAuthenticationToken.authenticated(userAuth, null, userAuth.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticated);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(final HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null) {
            return null;
        }
        return authorization.replace("Bearer ", "");
    }
}
