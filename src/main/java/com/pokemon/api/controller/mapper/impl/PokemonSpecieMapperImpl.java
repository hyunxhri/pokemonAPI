package com.pokemon.api.controller.mapper.impl;

import com.pokemon.api.controller.mapper.PokemonSpecieMapper;
import com.pokemon.api.dto.PokemonSpecieDTO;
import com.pokemon.api.model.PokemonSpecie;
import org.springframework.stereotype.Component;

@Component
public class PokemonSpecieMapperImpl implements PokemonSpecieMapper {
    @Override
    public PokemonSpecieDTO toDTO(PokemonSpecie pokemonSpecie) {
        PokemonSpecieDTO dto = new PokemonSpecieDTO();
        dto.setPokemonId(pokemonSpecie.getPokemonId());
        dto.setName(pokemonSpecie.getName());
        dto.setHp(pokemonSpecie.getHp());
        dto.setAttack(pokemonSpecie.getAttack());
        dto.setDefense(pokemonSpecie.getDefense());
        dto.setSpeed(pokemonSpecie.getSpeed());
        dto.setSAttack(pokemonSpecie.getSAttack());
        dto.setSDefense(pokemonSpecie.getSDefense());
        dto.setImg(pokemonSpecie.getImg());
        dto.setImgShiny(pokemonSpecie.getImgShiny());
        dto.setType(pokemonSpecie.getType());
        dto.setType2(pokemonSpecie.getType2());
        return dto;
    }
}
