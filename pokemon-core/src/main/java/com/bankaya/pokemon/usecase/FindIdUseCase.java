package com.bankaya.pokemon.usecase;

import com.bankaya.pokemon.boundary.request.FindIdRequest;
import com.bankaya.pokemon.boundary.request.Request;
import com.bankaya.pokemon.boundary.response.FindIdResponse;
import com.bankaya.pokemon.boundary.response.Response;
import com.bankaya.pokemon.gateway.PokemonIdGateway;
import reactor.core.publisher.Mono;

import java.util.Map;

public class FindIdUseCase implements UseCase {
    private PokemonIdGateway pokemonIdGateway;

    public FindIdUseCase(PokemonIdGateway pokemonIdGateway) {
        this.pokemonIdGateway = pokemonIdGateway;
    }

    @Override
    public Mono<Response> execute(Request request) {
        Map<String,String> errors = request.validate();
        if(!errors.isEmpty())
            return Mono.just(Response.makeFailResponse(errors));

        return pokemonIdGateway.findIdByName(((FindIdRequest)request).name)
                .map(id -> makeFindIdResponse(id));
    }

    private Response makeFindIdResponse(Long id) {
        var response = new FindIdResponse();
        response.success = Boolean.TRUE;
        response.id = id;
        return response;
    }
}
