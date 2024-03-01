package com.pokemon.api.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.pokemon.api.exception.InvalidAuthenticationException;
import com.pokemon.api.service.TokenExtractor;
import com.pokemon.api.service.TokenGenerator;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class JWTTokenManager implements TokenGenerator, TokenExtractor {

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
                .withExpiresAt(Instant.now().plusSeconds(4800))
                .withJWTId(UUID.randomUUID().toString())
                //Can't use the token before the date
                .withNotBefore(Instant.now())
                .sign(this.algorithm);
    }

    private DecodedJWT decode(String token) {
        try {
            return jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new InvalidAuthenticationException("Token provided was not correct");
        }
    }

    @Override
    public Long getUser(String token) {

        String extractedToken = token;

        if (token.startsWith("Bearer ")) {
            extractedToken = token.substring(7);
        }

        DecodedJWT decodedToken = decode(extractedToken);
        return decodedToken.getClaim("userId").asLong();

    }
}
