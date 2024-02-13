package com.pokemon.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "pokedex")
public class Pokedex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Short progress;
    private Short numPokemons;

}
