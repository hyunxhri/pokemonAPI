package com.pokemon.api.exception;

public class PokedexNotFoundException extends RuntimeException{
    public PokedexNotFoundException(String message){
        super(message);
    }
}