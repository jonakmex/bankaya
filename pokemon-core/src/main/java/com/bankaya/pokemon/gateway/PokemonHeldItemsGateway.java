package com.bankaya.pokemon.gateway;

import com.bankaya.pokemon.entity.HeldItem;
import reactor.core.publisher.Flux;

public interface PokemonHeldItemsGateway {
    Flux<HeldItem> findHeldItems(String name);
}
