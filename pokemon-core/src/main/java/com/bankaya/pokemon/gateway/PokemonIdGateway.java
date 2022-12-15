package com.bankaya.pokemon.gateway;

import reactor.core.publisher.Mono;

public interface PokemonIdGateway {
    Mono<Long> findIdByName(String pokemonName);
}
