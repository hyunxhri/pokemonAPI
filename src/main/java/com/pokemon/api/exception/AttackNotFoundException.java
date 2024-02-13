package com.pokemon.api.exception;

public class AttackNotFoundException extends RuntimeException{
    public AttackNotFoundException(String message){
        super(message);
    }
}