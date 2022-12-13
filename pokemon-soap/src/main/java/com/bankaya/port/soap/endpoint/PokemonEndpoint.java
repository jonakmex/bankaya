package com.bankaya.port.soap.endpoint;


import com.bankaya.pokemon.boundary.RequestFactory;
import com.bankaya.pokemon.boundary.ds.AbilityDS;
import com.bankaya.pokemon.boundary.request.Request;
import com.bankaya.pokemon.usecase.UseCase;
import com.bankaya.pokemon.usecase.UseCaseFactory;
import com.bankaya.pokemon_web_service.Ability;
import com.bankaya.pokemon_web_service.FindAbilitiesSoapRequest;
import com.bankaya.pokemon_web_service.FindAbilitiesSoapResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Collections;
import java.util.stream.Collectors;

@Endpoint
public class PokemonEndpoint {
    private static final String NAMESPACE_URI = "http://bankaya.com/pokemon-web-service";
    private UseCaseFactory useCaseFactory;
    private RequestFactory requestFactory;

    public PokemonEndpoint(UseCaseFactory useCaseFactory, RequestFactory requestFactory) {
        this.useCaseFactory = useCaseFactory;
        this.requestFactory = requestFactory;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findAbilitiesSoapRequest")
    @ResponsePayload
    public FindAbilitiesSoapResponse findAbilities(@RequestPayload FindAbilitiesSoapRequest request) {
        FindAbilitiesSoapResponse findAbilitiesSoapResponse = new FindAbilitiesSoapResponse();
        UseCase useCase = useCaseFactory.make("FindAbilitiesUseCase");
        Request findAbilitiesRequest = requestFactory
                .make("FindAbilitiesRequest", Collections.singletonMap("name",request.getPokemonName()));

        useCase.execute(findAbilitiesRequest)
                        .map(r -> (com.bankaya.pokemon.boundary.response.FindAbilitiesResponse)r)
                        .subscribe(r -> {
                            findAbilitiesSoapResponse.getAbilities().addAll(
                                    r.abilities.stream().map(a -> mapToSoapResponse(a)).collect(Collectors.toList())
                            );
                        });

        return findAbilitiesSoapResponse;
    }

    private Ability mapToSoapResponse(AbilityDS a) {
        Ability ability = new Ability();
        ability.setName(a.getName());
        ability.setSlot(a.slot);
        ability.setHidden(a.hidden);
        return ability;
    }
}
