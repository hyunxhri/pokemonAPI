package com.pokemon.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserPokedexDTO {

    private Long id;
    private Short progress;
    private Short numPokemons;
    private List<PokedexPokemonDTO> pokedexPokemon;

}
