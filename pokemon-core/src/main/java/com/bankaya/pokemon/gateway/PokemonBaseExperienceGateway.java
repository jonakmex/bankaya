package com.bankaya.pokemon.gateway;

import reactor.core.publisher.Mono;

public interface PokemonBaseExperienceGateway {
    Mono<Integer> findBaseExperienceByName(String name);
}
