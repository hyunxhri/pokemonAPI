package com.pokemon.api.dto;

import lombok.Data;

@Data
public class PokedexPokemonDTO {

    private Short pokemonId;
    private Boolean seen;
    private Boolean favorite;
    private Boolean captured;

}
