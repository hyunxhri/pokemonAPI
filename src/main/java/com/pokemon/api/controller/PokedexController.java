package com.pokemon.api.controller;

import com.pokemon.api.dto.PokedexPokemonDTO;
import com.pokemon.api.dto.UpdatePokemonDTO;
import com.pokemon.api.dto.UserPokedexDTO;
import com.pokemon.api.model.PokedexPokemon;
import com.pokemon.api.service.PokedexService;
import com.pokemon.api.service.TokenExtractor;
import com.pokemon.api.service.output.PokedexPokemonOutput;
import com.pokemon.api.service.output.UserPokedexOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokedex")
@RequiredArgsConstructor
public class PokedexController {

    private final PokedexService pokedexService;
    private final TokenExtractor tokenExtractor;


    @GetMapping
    public UserPokedexDTO getPokedex(@RequestHeader(name = "Authorization") String token) {
        return toDto(pokedexService.getPokedex(tokenExtractor.getUser(token)));
    }

    @GetMapping("/favorites")
    public List<PokedexPokemonDTO> getFavorites(@RequestHeader(name = "Authorization") String token) {
        return pokedexService.getFavorites(tokenExtractor.getUser(token)).stream()
                .map(this::toPokemonPokedexDto).toList();
    }

    @PutMapping("/pokemons/{pokemonId}")
    public void updatePokemon(@RequestHeader(name = "Authorization") String token, @PathVariable Short pokemonId, @RequestBody UpdatePokemonDTO updatePokemonDTO) {
        Long user = tokenExtractor.getUser(token);

        if (updatePokemonDTO.getFavorite() != null) {
            if (updatePokemonDTO.getFavorite()) {
                pokedexService.addFavorite(user, pokemonId);
            } else {
                pokedexService.removeFavorite(user, pokemonId);
            }
        }

        if (updatePokemonDTO.getCaptured() != null) {
            if (updatePokemonDTO.getCaptured()) {
                pokedexService.capture(user, pokemonId);
            }
        }

        if (updatePokemonDTO.getSeen() != null) {
            if (updatePokemonDTO.getSeen()){
                pokedexService.seePokemon(user, pokemonId);
            }
        }

    }

    private UserPokedexDTO toDto(UserPokedexOutput output) {
        UserPokedexDTO dto = new UserPokedexDTO();
        dto.setId(output.getId());
        dto.setNumPokemons(output.getNumPokemons());
        dto.setProgress(output.getProgress());
        dto.setPokedexPokemon(output.getPokedexPokemon().stream()
                .map(this::toPokemonPokedexDto)
                .toList());

        return dto;
    }

    private PokedexPokemonDTO toPokemonPokedexDto(PokedexPokemonOutput output) {
        PokedexPokemonDTO dto = new PokedexPokemonDTO();
        dto.setPokemonId(output.getPokemonId());
        dto.setSeen(output.getSeen());
        dto.setCaptured(output.getCaptured());
        dto.setFavorite(output.getFavorite());

        return dto;
    }

    private PokedexPokemonDTO toPokemonPokedexDto(PokedexPokemon pkpokemon) {
        PokedexPokemonDTO dto = new PokedexPokemonDTO();
        dto.setPokemonId(pkpokemon.getPokemon().getPokemonId());
        dto.setSeen(pkpokemon.getSeen());
        dto.setCaptured(pkpokemon.getCaptured());
        dto.setFavorite(pkpokemon.getFavorite());

        return dto;
    }

}
