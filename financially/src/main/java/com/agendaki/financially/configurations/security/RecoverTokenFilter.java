package com.agendaki.financially.configurations.security;

import com.agendaki.financially.models.preuser.PreUser;
import com.agendaki.financially.repositories.PreUserRepository;
import com.agendaki.financially.services.jwt.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RecoverTokenFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final PreUserRepository preUserRepository;

    public RecoverTokenFilter(final JWTService jwtService, final PreUserRepository preUserRepository) {
        this.jwtService = jwtService;
        this.preUserRepository = preUserRepository;
    }

    @Override
    @Transactional
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);
        if (token != null) {
            String subject = jwtService.verifyToken(token);
            PreUserRepository.PreUserAuth userAuth = preUserRepository.findByEmail(subject).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            PreUser preUser = new PreUser(userAuth);
            UsernamePasswordAuthenticationToken authenticated = UsernamePasswordAuthenticationToken.authenticated(preUser, preUser.getId(), preUser.getAuthorities());
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
