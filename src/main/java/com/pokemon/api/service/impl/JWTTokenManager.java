package com.pokemon.api.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.pokemon.api.service.TokenGenerator;
import com.pokemon.api.service.TokenValidator;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class JWTTokenManager implements TokenGenerator, TokenValidator {

    private final Algorithm algorithm;
    private final JWTVerifier jwtVerifier;
    private final String issuer;

    public JWTTokenManager() {
        this.issuer = "PokemonAPI";
        //Encrypts in HMAC256 with JWT
        this.algorithm = Algorithm.HMAC256("hola");
        this.jwtVerifier = JWT.require(this.algorithm)
                .withIssuer(this.issuer)
                .build();
    }

    @Override
    public String generate(Long userId) {
        return JWT.create()
                //Identity that generate token (pokemonAPI)
                .withIssuer(this.issuer)
                .withSubject(this.issuer)
                // Store the userId in the token
                .withClaim("userId", userId)
                // Moment when the token was created
                .withIssuedAt(Instant.now())
                //The token expires in 1 hour
                .withExpiresAt(Instant.now().plusSeconds(3600))
                .withJWTId(UUID.randomUUID().toString())
                //Can't use the token before the date
                .withNotBefore(Instant.now())
                .sign(this.algorithm);
    }

    @Override
    public boolean validate(String token) {
        return false;
    }
}
