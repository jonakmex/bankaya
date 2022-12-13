package com.bankaya.pokemon.gateway;

import com.bankaya.pokemon.entity.Ability;
import reactor.core.publisher.Flux;

import java.util.List;

public interface PokemonAbilitiesGateway {
    Flux<Ability> findAbilitiesByName(String pokemonName);
}
