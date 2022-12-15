package com.bankaya.pokemon.gateway;

import reactor.core.publisher.Mono;

public interface PokemonNameGateway {
    Mono<String> findName(String name);
}
