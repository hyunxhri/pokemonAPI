package com.pokemon.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String gender;
    private String img;
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Pokedex pokedex;

}
