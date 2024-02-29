package com.pokemon.api.service.output;

import lombok.Data;

import java.util.List;

@Data
public class UserPokedexOutput {

    private Long id;
    private Short progress;
    private Short numPokemons;
    private List<PokedexPokemonOutput> pokedexPokemon;

}
