package com.bankaya.pokemon.usecase;

import com.bankaya.pokemon.boundary.ds.EncounterDS;
import com.bankaya.pokemon.boundary.ds.EncounterDetailDS;
import com.bankaya.pokemon.boundary.request.FindBaseExperienceRequest;
import com.bankaya.pokemon.boundary.request.FindLocationEncountersRequest;
import com.bankaya.pokemon.boundary.request.Request;
import com.bankaya.pokemon.boundary.response.FindBaseExperienceResponse;
import com.bankaya.pokemon.boundary.response.FindLocationEncountersResponse;
import com.bankaya.pokemon.boundary.response.Response;
import com.bankaya.pokemon.entity.Encounter;
import com.bankaya.pokemon.entity.EncounterDetail;
import com.bankaya.pokemon.gateway.PokemonIdGateway;
import com.bankaya.pokemon.gateway.PokemonLocationEncountersGateway;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindLocationEncountersUseCase implements UseCase {
    private PokemonLocationEncountersGateway pokemonLocationEncountersGateway;
    private PokemonIdGateway pokemonIdGateway;

    public FindLocationEncountersUseCase(PokemonLocationEncountersGateway pokemonLocationEncountersGateway
            , PokemonIdGateway pokemonIdGateway) {
        this.pokemonLocationEncountersGateway = pokemonLocationEncountersGateway;
        this.pokemonIdGateway = pokemonIdGateway;
    }

    @Override
    public Mono<Response> execute(Request request) {
        Map<String,String> errors = request.validate();
        if(!errors.isEmpty())
            return Mono.just(Response.makeFailResponse(errors));

        return pokemonIdGateway.findIdByName(((FindLocationEncountersRequest)request).name)
                .flatMap(id -> pokemonLocationEncountersGateway.findLocationEncountersById(id)
                        .flatMap(encounter -> mapToEncounterDS(encounter))
                        .collectList())
                .flatMap(encounters -> makeFindLocationEncountersResponse(encounters));
    }

    private Mono<Response> makeFindLocationEncountersResponse(List<EncounterDS> encounters) {
        var response = new FindLocationEncountersResponse();
        response.success = Boolean.TRUE;
        response.encounters = encounters;
        return Mono.just(response);
    }

    private Mono<EncounterDS> mapToEncounterDS(Encounter encounter) {
        var encounterDS = new EncounterDS();
        encounterDS.locationArea = encounter.getLocationArea();
        encounterDS.encounterDetails = encounter.getEncounterDetails()
                .stream()
                .map(d -> mapToEncounterDetailDS(d))
                .collect(Collectors.toList());
        return Mono.just(encounterDS);
    }

    private EncounterDetailDS mapToEncounterDetailDS(EncounterDetail d) {
        var encounterDetailDS = new EncounterDetailDS();
        encounterDetailDS.chance = d.getChance();
        encounterDetailDS.conditionValues = d.getConditionValues();
        encounterDetailDS.minLevel = d.getMinLevel();
        encounterDetailDS.maxLevel = d.getMaxLevel();
        encounterDetailDS.method = d.getMethod();
        return encounterDetailDS;
    }
}
