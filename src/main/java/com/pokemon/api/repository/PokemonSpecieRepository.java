package com.pokemon.api.repository;

import com.pokemon.api.model.PokemonSpecie;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PokemonSpecieRepository extends JpaRepository<PokemonSpecie, Short>{
    Optional<PokemonSpecie> findByNameIgnoreCase(String name);
}
