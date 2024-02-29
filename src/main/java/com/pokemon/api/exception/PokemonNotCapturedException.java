package com.pokemon.api.exception;

public class PokemonNotCapturedException extends RuntimeException {

    public PokemonNotCapturedException(String message) {
        super(message);
    }
}
