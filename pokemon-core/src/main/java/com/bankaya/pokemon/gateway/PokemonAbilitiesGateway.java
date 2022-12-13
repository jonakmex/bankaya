package com.bankaya.pokemon.gateway;

import com.bankaya.pokemon.entity.Ability;

import java.util.List;

public interface PokemonAbilitiesGateway {
    List<Ability> findAbilitiesByName(String pokemonName);
}
