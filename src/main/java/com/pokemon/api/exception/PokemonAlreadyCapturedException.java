package com.pokemon.api.exception;

public class PokemonAlreadyCapturedException extends RuntimeException{
        public PokemonAlreadyCapturedException(String message){
            super(message);
        }

}
