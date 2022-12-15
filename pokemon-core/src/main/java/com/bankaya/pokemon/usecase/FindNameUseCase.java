package com.bankaya.pokemon.usecase;

import com.bankaya.pokemon.boundary.request.FindNameRequest;
import com.bankaya.pokemon.boundary.request.Request;
import com.bankaya.pokemon.boundary.response.FindNameResponse;
import com.bankaya.pokemon.boundary.response.Response;
import com.bankaya.pokemon.gateway.PokemonNameGateway;
import reactor.core.publisher.Mono;

import java.util.Map;

public class FindNameUseCase implements UseCase {
    private PokemonNameGateway pokemonNameGateway;

    public FindNameUseCase(PokemonNameGateway pokemonNameGateway) {
        this.pokemonNameGateway = pokemonNameGateway;
    }

    @Override
    public Mono<Response> execute(Request request) {
        Map<String,String> errors = request.validate();
        if(!errors.isEmpty())
            return Mono.just(Response.makeFailResponse(errors));

        return pokemonNameGateway.findName(((FindNameRequest)request).name)
                .map(response -> makeFindNameResponse(response));
    }

    private Response makeFindNameResponse(String name) {
        var response = new FindNameResponse();
        response.success = Boolean.TRUE;
        response.name = name;
        return response;
    }
}
