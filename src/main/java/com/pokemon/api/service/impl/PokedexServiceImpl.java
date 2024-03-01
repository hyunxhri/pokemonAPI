package com.pokemon.api.service.impl;

import com.pokemon.api.exception.MaxPokemonFavoriteException;
import com.pokemon.api.exception.PokemonAlreadyCapturedException;
import com.pokemon.api.exception.PokemonNotCapturedException;
import com.pokemon.api.model.Pokedex;
import com.pokemon.api.model.PokedexPokemon;
import com.pokemon.api.repository.PokedexPokemonRepository;
import com.pokemon.api.repository.PokedexRepository;
import com.pokemon.api.repository.PokemonSpecieRepository;
import com.pokemon.api.service.PokedexService;
import com.pokemon.api.service.output.PokedexPokemonOutput;
import com.pokemon.api.service.output.UserPokedexOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PokedexServiceImpl implements PokedexService {

    private final PokedexRepository pokedexRepository;
    private final PokedexPokemonRepository pokedexPokemonRepository;
    private final PokemonSpecieRepository pokemonSpecieRepository;
    private static final Short MAX_FAVORITES = 6;

    @Override
    public UserPokedexOutput getPokedex(Long userId) {

        Pokedex pokedex = pokedexRepository.findByUserId(userId);
        List<PokedexPokemon> pokedexPokemons = pokedexPokemonRepository.findByPokedexId(pokedex.getId());

        return toUserPokemonOutput(pokedex, pokedexPokemons);
    }

    @Override
    public void addFavorite(Long userId, Short pokemonId) {

        Pokedex pokedex = pokedexRepository.findByUserId(userId);

        Short favorites = pokedexPokemonRepository.countByPokedexIdAndFavoriteTrue(pokedex.getId());

        if (MAX_FAVORITES.equals(favorites)) {
            throw new MaxPokemonFavoriteException("Max pokemons favorite");
        }

        PokedexPokemon pokemon = pokedexPokemonRepository.findByPokedexIdAndPokemonPokemonId(pokedex.getId(), pokemonId);

        if (pokemon.getCaptured()) {
            pokemon.setFavorite(true);
        } else throw new PokemonNotCapturedException("Pokemon with id " + pokemonId + " is not captured");

        pokedexPokemonRepository.save(pokemon);

    }

    @Override
    public void removeFavorite(Long userId, Short pokemonId) {

        Pokedex pokedex = pokedexRepository.findByUserId(userId);

        PokedexPokemon pokemon = pokedexPokemonRepository.findByPokedexIdAndPokemonPokemonId(pokedex.getId(), pokemonId);

        if (pokemon.getCaptured()) {
            pokemon.setFavorite(false);
        } else throw new PokemonNotCapturedException("Pokemon with id " + pokemonId + " is not captured");

        pokedexPokemonRepository.save(pokemon);

    }

    @Override
    public void capture(Long userId, Short pokemonId) {

        Pokedex pokedex = pokedexRepository.findByUserId(userId);

        PokedexPokemon pokemon = pokedex.getPokedexPokemon().stream()
                .filter(p -> p.getPokemon().getPokemonId().equals(pokemonId))
                .filter(p -> !p.getCaptured())
                .findFirst()
                .orElseThrow(() -> new PokemonAlreadyCapturedException("Pokemon with id " + pokemonId + " is already captured"));

        pokemon.setPokemon(pokemonSpecieRepository.getReferenceById(pokemonId));
        pokemon.setCaptured(true);
        pokemon.setSeen(true);
        pokemon.setFavorite(false);
        pokemon.setPokedex(pokedex);
        pokedexPokemonRepository.save(pokemon);

    }

    @Override
    public void seePokemon(Long userId, short pokemonId) {
        Pokedex pokedex = pokedexRepository.findByUserId(userId);

        PokedexPokemon pokemon = pokedexPokemonRepository.findByPokedexIdAndPokemonPokemonId(pokedex.getId(), pokemonId);

        if (pokemon == null) {
            pokemon = new PokedexPokemon();
            pokemon.setPokedex(pokedex);
            pokemon.setCaptured(false);
            pokemon.setFavorite(false);
            pokemon.setPokemon(pokemonSpecieRepository.getReferenceById(pokemonId));
        }

        pokemon.setSeen(true);

        pokedexPokemonRepository.save(pokemon);
    }

    @Override
    public List<PokedexPokemon> getFavorites(Long userId) {
        Pokedex pokedex = pokedexRepository.findByUserId(userId);
        return pokedexPokemonRepository.findByPokedexIdAndFavoriteTrue(pokedex.getId());
    }

    private UserPokedexOutput toUserPokemonOutput(Pokedex pokedex, List<PokedexPokemon> pokedexPokemons) {
        UserPokedexOutput output = new UserPokedexOutput();
        output.setPokedexPokemon(pokedexPokemons.stream()
                .map(this::toPokedexPokemonOutput)
                .toList()
        );
        output.setNumPokemons(pokedex.getNumPokemons());
        output.setProgress(pokedex.getProgress());
        output.setId(pokedex.getId());
        return output;
    }

    private PokedexPokemonOutput toPokedexPokemonOutput(PokedexPokemon pokedexPokemon) {
        PokedexPokemonOutput output = new PokedexPokemonOutput();
        output.setPokemonId(pokedexPokemon.getPokemon().getPokemonId());
        output.setSeen(pokedexPokemon.getSeen());
        output.setCaptured(pokedexPokemon.getCaptured());
        output.setFavorite(pokedexPokemon.getFavorite());
        return output;
    }
}
