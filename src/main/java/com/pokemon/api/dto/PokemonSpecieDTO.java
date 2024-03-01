package com.pokemon.api.dto;

import lombok.Data;

@Data
public class PokemonSpecieDTO {

    private Short pokemonId;
    private String name;
    private String type;
    private String type2;
    private String img;
    private String imgShiny;
    private Short hp;
    private Short attack;
    private Short defense;
    private Short sAttack;
    private Short sDefense;
    private Short speed;

}
