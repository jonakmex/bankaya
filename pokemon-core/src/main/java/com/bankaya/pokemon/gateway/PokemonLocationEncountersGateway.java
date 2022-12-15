package com.bankaya.pokemon.gateway;

import com.bankaya.pokemon.entity.Encounter;
import reactor.core.publisher.Flux;

public interface PokemonLocationEncountersGateway {
    Flux<Encounter> findLocationEncountersById(Long id);
}
