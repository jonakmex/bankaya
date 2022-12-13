package com.bankaya.pokemon.usecase;

import com.bankaya.pokemon.boundary.request.Request;
import com.bankaya.pokemon.boundary.response.Response;
import com.bankaya.pokemon.gateway.PokemonAbilitiesGateway;
import reactor.core.publisher.Mono;

public class FindAbilitiesUseCase implements UseCase {
    private PokemonAbilitiesGateway pokemonAbilitiesGateway;
    @Override
    public Mono<Response> execute(Request request) {
        return null;
    }
}
