package com.bankaya.port.soap.endpoint;


import com.bankaya.pokemon.boundary.RequestFactory;
import com.bankaya.pokemon.boundary.ds.*;
import com.bankaya.pokemon.boundary.request.Request;
import com.bankaya.pokemon.boundary.response.*;
import com.bankaya.pokemon.usecase.UseCase;
import com.bankaya.pokemon.usecase.UseCaseFactory;
import com.bankaya.pokemon_web_service.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
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

    @PayloadRoot( namespace = NAMESPACE_URI, localPart = "findAbilitiesSoapRequest")
    @ResponsePayload
    public FindAbilitiesSoapResponse findAbilities(@RequestPayload FindAbilitiesSoapRequest request) {
        FindAbilitiesSoapResponse findAbilitiesSoapResponse = new FindAbilitiesSoapResponse();
        UseCase useCase = useCaseFactory.make("FindAbilitiesUseCase");
        Request findAbilitiesRequest = requestFactory
                .make("FindAbilitiesRequest", Collections.singletonMap("name",request.getPokemonName()));

        useCase.execute(findAbilitiesRequest)
                        .map(r -> (com.bankaya.pokemon.boundary.response.FindAbilitiesResponse)r)
                        .subscribe(r -> {
                            Abilities abilities = new Abilities();
                            abilities.getAbility().addAll(
                                    r.abilities.stream().map(a -> mapToSoapResponse(a)).collect(Collectors.toList())
                            );
                            findAbilitiesSoapResponse.setAbilities(abilities);
                        });

        return findAbilitiesSoapResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findBaseExperienceSoapRequest")
    @ResponsePayload
    public FindBaseExperienceSoapResponse findBaseExperience(@RequestPayload FindBaseExperienceSoapRequest request) {
        FindBaseExperienceSoapResponse findBaseExperienceSoapResponse = new FindBaseExperienceSoapResponse();
        UseCase useCase = useCaseFactory.make("FindBaseExperienceUseCase");
        Request findBaseExperienceRequest = requestFactory
                .make("FindBaseExperienceRequest", Collections.singletonMap("name",request.getPokemonName()));

        useCase.execute(findBaseExperienceRequest)
                .map(r -> (FindBaseExperienceResponse)r)
                .subscribe(r -> {
                    findBaseExperienceSoapResponse.setBaseExperience(BigInteger.valueOf(r.baseExperience));
                });
        return findBaseExperienceSoapResponse;
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findHeldItemsSoapRequest")
    @ResponsePayload
    public FindHeldItemsSoapResponse findHeldItems(@RequestPayload FindHeldItemsSoapRequest request) {
        FindHeldItemsSoapResponse findHeldItemsSoapResponse = new FindHeldItemsSoapResponse();
        UseCase useCase = useCaseFactory.make("FindHeldItemsUseCase");
        Request findHeldItemsRequest = requestFactory
                .make("FindHeldItemsRequest", Collections.singletonMap("name",request.getPokemonName()));

        useCase.execute(findHeldItemsRequest)
                .map(r -> (FindHeldItemsResponse)r)
                .subscribe(r -> {
                    findHeldItemsSoapResponse.setHelditems(mapToHeldItemSoapResponse(r.heldItems));
                });

        return findHeldItemsSoapResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findIdSoapRequest")
    @ResponsePayload
    public FindIdSoapResponse findId(@RequestPayload FindIdSoapRequest request) {
        FindIdSoapResponse findIdSoapResponse = new FindIdSoapResponse();
        UseCase useCase = useCaseFactory.make("FindIdUseCase");
        Request findBaseExperienceRequest = requestFactory
                .make("FindIdRequest", Collections.singletonMap("name",request.getPokemonName()));

        useCase.execute(findBaseExperienceRequest)
                .map(r -> (FindIdResponse)r)
                .subscribe(r -> {
                    findIdSoapResponse.setId(BigInteger.valueOf(r.id));
                });
        return findIdSoapResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findNameSoapRequest")
    @ResponsePayload
    public FindNameSoapResponse findName(@RequestPayload FindNameSoapRequest request) {
        FindNameSoapResponse findNameSoapResponse = new FindNameSoapResponse();
        UseCase useCase = useCaseFactory.make("FindNameUseCase");
        Request findNameRequest = requestFactory
                .make("FindNameRequest", Collections.singletonMap("name",request.getPokemonName()));

        useCase.execute(findNameRequest)
                .map(r -> (FindNameResponse)r)
                .subscribe(r -> {
                    findNameSoapResponse.setName(r.name);
                });
        return findNameSoapResponse;
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findLocationAreaEncountersSoapRequest")
    @ResponsePayload
    public FindLocationAreaEncountersSoapResponse findLocationAreaEncounters(@RequestPayload FindLocationAreaEncountersSoapRequest request) {
        var findLocationAreaEncountersSoapResponse = new FindLocationAreaEncountersSoapResponse();
        UseCase useCase = useCaseFactory.make("FindLocationEncountersUseCase");
        Request findLocationEncountersRequest = requestFactory
                .make("FindLocationEncountersRequest", Collections.singletonMap("name",request.getPokemonName()));

        useCase.execute(findLocationEncountersRequest)
                .map(r -> (FindLocationEncountersResponse)r)
                .subscribe(r -> {
                    findLocationAreaEncountersSoapResponse.setEncounters(mapToLocationEncountersSoap(r.encounters));
                });
        return findLocationAreaEncountersSoapResponse;
    }

    private Encounters mapToLocationEncountersSoap(List<EncounterDS> encountersDs) {
        var encounters = new Encounters();
        encounters.getEncounter().addAll(encountersDs
                .stream()
                .map(ds -> mapToEncounterSoap(ds))
                .collect(Collectors.toList()));
        return encounters;
    }

    private Encounter mapToEncounterSoap(EncounterDS ds) {
        var encounter = new Encounter();
        encounter.setLocationArea(ds.locationArea);
        //encounter.setEncounterDetails(mapToEncounterDetails(ds));
        return encounter;
    }

    private EncounterDetails mapToEncounterDetails(EncounterDS ds) {
        var encounterDetails = new EncounterDetails();
        encounterDetails.getEncounterDetail().addAll(ds.encounterDetails
                .stream()
                .map(dtl -> mapToEncounterDetailSoap(dtl))
                .collect(Collectors.toList()));
        return encounterDetails;
    }

    private EncounterDetail mapToEncounterDetailSoap(EncounterDetailDS dtl) {
        var encounterDetail = new EncounterDetail();
        encounterDetail.setChance(BigInteger.valueOf(dtl.chance));
        encounterDetail.setConditionValues(dtl.conditionValues);
        encounterDetail.setMaxLevel(BigInteger.valueOf(dtl.maxLevel));
        encounterDetail.setMethod(dtl.method);
        encounterDetail.setMinLevel(BigInteger.valueOf(dtl.minLevel));
        return encounterDetail;
    }


    private Ability mapToSoapResponse(AbilityDS a) {
        Ability ability = new Ability();
        ability.setName(a.getName());
        ability.setSlot(BigInteger.valueOf(a.slot));
        ability.setHidden(a.hidden);
        return ability;
    }

    private com.bankaya.pokemon_web_service.Helditems mapToHeldItemSoapResponse(List<HeldItemDS> heldItemsDs) {
        var heldItemsSoap = new com.bankaya.pokemon_web_service.Helditems();
        heldItemsSoap.getHelditem().addAll(heldItemsDs
                .stream()
                .map(ds -> mapToHeldItemSoapResponse(ds))
                .collect(Collectors.toList()));
        return heldItemsSoap;
    }

    private com.bankaya.pokemon_web_service.Helditem mapToHeldItemSoapResponse(HeldItemDS heldItemDs) {
        var heldItemSoap = new com.bankaya.pokemon_web_service.Helditem();
        heldItemSoap.setName(heldItemDs.name);
        heldItemSoap.setVersiondetails(mapToVersionDetailsSoap(heldItemDs.details));
        return heldItemSoap;
    }

    private Versiondetails mapToVersionDetailsSoap(List<HeldDetailDS> details) {
        var versionDetailsSoap = new Versiondetails();
        versionDetailsSoap.getVersiondetail().addAll(details
                .stream()
                .map(ds -> mapToVersionDetailsSoap(ds))
                .collect(Collectors.toList()));
        return versionDetailsSoap;
    }

    private Versiondetail mapToVersionDetailsSoap(HeldDetailDS detail) {
        var versionDetailSoap = new Versiondetail();
        versionDetailSoap.setName(detail.name);
        versionDetailSoap.setRarity(BigInteger.valueOf(detail.rarity));
        return versionDetailSoap;
    }
}
