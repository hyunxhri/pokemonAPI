package com.pokemon.api.service;

import com.pokemon.api.model.PokedexPokemon;
import com.pokemon.api.service.output.UserPokedexOutput;

import java.util.List;

public interface PokedexService {

    UserPokedexOutput getPokedex(Long userId);

    void addFavorite(Long userId, Short pokemonId);

    void removeFavorite(Long userId, Short pokemonId);

    void capture(Long userId, Short pokemonId);

    void seePokemon(Long userId, short pokemonId);

    List<PokedexPokemon> getFavorites(Long userId);

}
