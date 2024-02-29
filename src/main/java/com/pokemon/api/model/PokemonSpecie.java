package com.pokemon.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "pokemon_specie")
public class PokemonSpecie {

    @Id
    private Short pokemonId;
    private String name;
    private String type;
    private String type2;
    private String img;
    private String imgShiny;
    private Short hp;
    private Short attack;
    private Short defense;
    private Short sAttack;
    private Short sDefense;
    private Short speed;
    @OneToMany(mappedBy = "pokemon")
    private List<PokedexPokemon> pokedexPokemon;

}
