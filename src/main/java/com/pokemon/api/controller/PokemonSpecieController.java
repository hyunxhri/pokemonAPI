package com.pokemon.api.controller;

import com.pokemon.api.controller.mapper.PokemonSpecieMapper;
import com.pokemon.api.dto.PokemonSpecieDTO;
import com.pokemon.api.exception.PokemonNotFoundException;
import com.pokemon.api.model.PokemonSpecie;
import com.pokemon.api.repository.PokemonSpecieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pokemons")
public class PokemonSpecieController {

    private final PokemonSpecieRepository pokemonSpecieRepository;
    private final PokemonSpecieMapper pokemonSpecieMapper;

    @GetMapping
    public Page<PokemonSpecieDTO> getPokemons(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "32") int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<PokemonSpecie> pokemons = pokemonSpecieRepository.findAll(pageRequest);
        List<PokemonSpecieDTO> pokemonsDTO = pokemons.getContent().stream().map(pokemonSpecieMapper::toDTO).toList();
        return new PageImpl<>(pokemonsDTO, pokemons.getPageable(), pokemons.getTotalElements());

    }

    @GetMapping("/{identifier}")

    // TO DO SEPARAR EN DOS ENDPOINTS /name/{name} y /id/{id}
    public PokemonSpecie getPokemon(@PathVariable String identifier) {
        if (identifier.matches("\\d+")) {
            Short pokemonId = Short.parseShort(identifier);
            return pokemonSpecieRepository.findById(pokemonId)
                    .orElseThrow(() -> new PokemonNotFoundException("Pokemon with id " + identifier + " not found."));
        } else {
            return pokemonSpecieRepository.findByNameIgnoreCase(identifier)
                    .orElseThrow(() -> new PokemonNotFoundException("Pokemon with name " + identifier + " not found."));
        }
    }

    @GetMapping("/random")
    public PokemonSpecie getPokemon() {
        int max = (int) pokemonSpecieRepository.count();
        Random random = new Random();
        int randomId = random.nextInt(1, max);
        return pokemonSpecieRepository.findById((short) randomId).orElseThrow(() -> new PokemonNotFoundException("Pokemon with id " + randomId + " not found."));
    }
}
