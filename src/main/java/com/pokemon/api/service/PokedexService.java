package com.pokemon.api.service;

import com.pokemon.api.service.output.UserPokedexOutput;

public interface PokedexService {

    UserPokedexOutput getPokedex(Long userId);

    void addFavorite(Long userId, Short pokemonId);

    void removeFavorite(Long userId, Short pokemonId);

}
