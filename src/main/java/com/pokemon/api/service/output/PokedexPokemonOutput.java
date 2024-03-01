package com.pokemon.api.service.output;

import lombok.Data;

@Data
public class PokedexPokemonOutput {

    private Short pokemonId;
    private Boolean seen;
    private Boolean favorite;
    private Boolean captured;

}
