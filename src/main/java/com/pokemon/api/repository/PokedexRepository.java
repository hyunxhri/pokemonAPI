package com.pokemon.api.repository;

import com.pokemon.api.model.Pokedex;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokedexRepository extends JpaRepository<Pokedex, Long> {

    Pokedex findByUserId(Long userId);

}
