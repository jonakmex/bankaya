package com.bankaya.pokemon.usecase;

import com.bankaya.pokemon.boundary.ds.HeldDetailDS;
import com.bankaya.pokemon.boundary.ds.HeldItemDS;
import com.bankaya.pokemon.boundary.request.FindHeldItemsRequest;
import com.bankaya.pokemon.boundary.request.Request;
import com.bankaya.pokemon.boundary.response.FindHeldItemsResponse;
import com.bankaya.pokemon.boundary.response.Response;
import com.bankaya.pokemon.entity.HeldDetail;
import com.bankaya.pokemon.entity.HeldItem;
import com.bankaya.pokemon.gateway.PokemonHeldItemsGateway;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindHeldItemsUseCase implements UseCase {
    private PokemonHeldItemsGateway pokemonHeldItemsGateway;

    public FindHeldItemsUseCase(PokemonHeldItemsGateway pokemonHeldItemsGateway) {
        this.pokemonHeldItemsGateway = pokemonHeldItemsGateway;
    }

    @Override
    public Mono<Response> execute(Request request) {
        Map<String,String> errors = request.validate();
        if(!errors.isEmpty())
            return Mono.just(Response.makeFailResponse(errors));

        return pokemonHeldItemsGateway.findHeldItems(((FindHeldItemsRequest)request).name)
                .map(h -> mapToHeldItemDS(h))
                .collectList()
                .map(heldItems -> makeFindHeldItemsResponse(heldItems));
    }

    private HeldItemDS mapToHeldItemDS(HeldItem h) {
        var heldItemDS = new HeldItemDS();
        heldItemDS.name = h.getName();
        heldItemDS.details = h.getDetails()
                .stream().map(d -> mapToHeldDetailDS(d))
                .collect(Collectors.toList());
        return heldItemDS;
    }

    private HeldDetailDS mapToHeldDetailDS(HeldDetail d) {
        var heldDetailDS = new HeldDetailDS();
        heldDetailDS.name = d.getName();
        heldDetailDS.rarity = d.getRarity();
        return heldDetailDS;
    }

    private Response makeFindHeldItemsResponse(List<HeldItemDS> heldItems) {
        var response = new FindHeldItemsResponse();
        response.success = Boolean.TRUE;
        response.heldItems = heldItems;
        return response;
    }
}
