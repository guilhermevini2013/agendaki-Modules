package com.agendaki.scheduling.services.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class JWTService {
    private final String nameApplication = "AgendaKi";

    public String generateToken(String email) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(nameApplication);
            return JWT.create()
                    .withIssuer(nameApplication)
                    .withSubject(email)
                    .withExpiresAt(Instant.now().plusSeconds(259200))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException(exception);
        }
    }

    public String verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(nameApplication);
            return JWT.require(algorithm)
                    .withIssuer(nameApplication)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException(exception);
        }
    }
}
