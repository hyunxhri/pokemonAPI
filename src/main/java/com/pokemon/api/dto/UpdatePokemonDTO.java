package com.pokemon.api.dto;

import lombok.Data;

@Data
public class UpdatePokemonDTO {

    private Boolean seen;
    private Boolean favorite;
    private Boolean captured;

}
