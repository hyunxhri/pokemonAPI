package com.pokemon.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "attacks")
public class Attack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String description;

}
