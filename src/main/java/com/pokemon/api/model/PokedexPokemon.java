package com.pokemon.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pokedex_pokemon")
public class PokedexPokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Pokedex pokedex;
    @ManyToOne
    private PokemonSpecie pokemon;
    private Boolean seen;
    private Boolean captured;
    private Boolean favorite;


}
