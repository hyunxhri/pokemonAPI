package com.pokemon.api.controller;

import com.pokemon.api.dto.PokedexPokemonDTO;
import com.pokemon.api.dto.UpdatePokemonDTO;
import com.pokemon.api.dto.UserPokedexDTO;
import com.pokemon.api.service.PokedexService;
import com.pokemon.api.service.TokenExtractor;
import com.pokemon.api.service.output.PokedexPokemonOutput;
import com.pokemon.api.service.output.UserPokedexOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/pokemons/{pokemonId}")
    public void markFavorite(@RequestHeader(name = "Authorization") String token, @PathVariable Short pokemonId, @RequestBody UpdatePokemonDTO updatePokemonDTO) {
        Long user = tokenExtractor.getUser(token);
        if (updatePokemonDTO.getFavorite())
            pokedexService.addFavorite(user, pokemonId);
        else pokedexService.removeFavorite(user, pokemonId);
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
        dto.setCaptured(output.getCaptured());
        dto.setFavorite(output.getFavorite());

        return dto;
    }

}
