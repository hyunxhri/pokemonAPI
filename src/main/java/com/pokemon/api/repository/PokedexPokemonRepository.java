package com.pokemon.api.repository;

import com.pokemon.api.model.PokedexPokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PokedexPokemonRepository extends JpaRepository<PokedexPokemon, Long> {

    List<PokedexPokemon> findByPokedexId(Long pokedexId);

    Short countByPokedexId(Long pokedexId);

    PokedexPokemon findByPokemonPokemonId(Short pokemonId);

}
