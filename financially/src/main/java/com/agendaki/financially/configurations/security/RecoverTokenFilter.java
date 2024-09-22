package com.agendaki.financially.configurations.security;

import com.agendaki.financially.exceptions.EntityNotFoundException;
import com.agendaki.financially.models.preuser.PreUser;
import com.agendaki.financially.repositories.PreUserRepository;
import com.agendaki.financially.services.jwt.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import java.io.IOException;

@Component
public class RecoverTokenFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final PreUserRepository preUserRepository;

    public RecoverTokenFilter(JWTService jwtService, PreUserRepository preUserRepository) {
        this.jwtService = jwtService;
        this.preUserRepository = preUserRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Cookie cookie = WebUtils.getCookie(httpRequest, "jwtPortalClient");
        if (cookie != null) {
            String token = cookie.getValue();
            if (token != null) {
                String subject = jwtService.verifyToken(token);
                PreUserRepository.PreUserAuth userAuth = preUserRepository.findByEmail(subject).orElseThrow(() -> new EntityNotFoundException("User not found"));
                PreUser preUser = new PreUser(userAuth);
                UsernamePasswordAuthenticationToken authenticated = UsernamePasswordAuthenticationToken.authenticated(preUser, preUser.getId(), preUser.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticated);
            }
        }
        filterChain.doFilter(request, response);
    }
}
