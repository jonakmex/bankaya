package com.bankaya.pokemon.usecase;

import com.bankaya.pokemon.boundary.request.FindBaseExperienceRequest;
import com.bankaya.pokemon.boundary.request.Request;
import com.bankaya.pokemon.boundary.response.FindBaseExperienceResponse;
import com.bankaya.pokemon.boundary.response.Response;
import com.bankaya.pokemon.gateway.PokemonBaseExperienceGateway;
import reactor.core.publisher.Mono;

import java.util.Map;

public class FindBaseExperienceUseCase implements UseCase {
    private PokemonBaseExperienceGateway pokemonBaseExperienceGateway;

    public FindBaseExperienceUseCase(PokemonBaseExperienceGateway pokemonBaseExperienceGateway) {
        this.pokemonBaseExperienceGateway = pokemonBaseExperienceGateway;
    }

    @Override
    public Mono<Response> execute(Request request) {
        Map<String,String> errors = request.validate();
        if(!errors.isEmpty())
            return Mono.just(Response.makeFailResponse(errors));

        return pokemonBaseExperienceGateway.findBaseExperienceByName(((FindBaseExperienceRequest)request).name)
                .map(baseExperience ->  makeFindBaseExperiencResponse(baseExperience));

    }

    private Response makeFindBaseExperiencResponse(Integer baseExperience) {
        var response = new FindBaseExperienceResponse();
        response.success = Boolean.TRUE;
        response.baseExperience = baseExperience;
        return response;
    }
}
