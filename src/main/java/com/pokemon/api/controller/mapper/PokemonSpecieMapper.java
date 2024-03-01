package com.pokemon.api.controller.mapper;

import com.pokemon.api.dto.PokemonSpecieDTO;
import com.pokemon.api.dto.UserDTO;
import com.pokemon.api.model.PokemonSpecie;
import com.pokemon.api.model.User;

public interface PokemonSpecieMapper {

    PokemonSpecieDTO toDTO(PokemonSpecie pokemonSpecie);

}
