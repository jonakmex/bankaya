package com.bankaya.pokemon.usecase;

import com.bankaya.pokemon.boundary.ds.AbilityDS;
import com.bankaya.pokemon.boundary.request.FindAbilitiesRequest;
import com.bankaya.pokemon.boundary.request.Request;
import com.bankaya.pokemon.boundary.response.FindAbilitiesResponse;
import com.bankaya.pokemon.boundary.response.Response;
import com.bankaya.pokemon.entity.Ability;
import com.bankaya.pokemon.gateway.PokemonAbilitiesGateway;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public class FindAbilitiesUseCase implements UseCase {
    private PokemonAbilitiesGateway pokemonAbilitiesGateway;

    public FindAbilitiesUseCase(PokemonAbilitiesGateway pokemonAbilitiesGateway) {
        this.pokemonAbilitiesGateway = pokemonAbilitiesGateway;
    }

    @Override
    public Mono<Response> execute(Request request) {

        Map<String,String> errors = request.validate();
        if(!errors.isEmpty())
            return Mono.just(Response.makeFailResponse(errors));

        FindAbilitiesRequest findAbilitiesRequest = (FindAbilitiesRequest) request;
        return pokemonAbilitiesGateway.findAbilitiesByName(findAbilitiesRequest.name)
                .flatMap(ability -> mapToAbilityDS(ability))
                .collectList()
                .map(abilities-> makeFindAbilitiesResponse(abilities));

    }

    private Response makeFindAbilitiesResponse(List<AbilityDS> abilities) {
        var findAbilitiesResponse = new FindAbilitiesResponse();
        findAbilitiesResponse.success = Boolean.TRUE;
        findAbilitiesResponse.abilities = abilities;
        return findAbilitiesResponse;
    }

    private Mono<AbilityDS> mapToAbilityDS(Ability ability) {
        AbilityDS abilityDS = new AbilityDS();
        abilityDS.name = ability.getName();
        abilityDS.slot = ability.getSlot();
        abilityDS.hidden = ability.getHidden();
        return Mono.just(abilityDS);
    }
}
