package com.bankaya.port.soap.gateway;

import com.bankaya.pokemon.entity.*;
import com.bankaya.pokemon.gateway.PokemonGateway;
import com.bankaya.port.soap.gateway.dto.*;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PokemonGatewayWebClient implements PokemonGateway {

    private WebClient webClient;

    public PokemonGatewayWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Flux<Ability> findAbilitiesByName(String pokemonName) {
        PokemonDto pokemon = webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/pokemon/{name}")
                        .build(pokemonName))
                .retrieve()
                .bodyToMono(PokemonDto.class)
                .block();

        return Flux.fromIterable(pokemon.getAbilities()
                                        .stream()
                                        .map(abilityDto -> mapToAbility(abilityDto))
                                        .collect(Collectors.toList()));
    }

    @Override
    public Mono<Integer> findBaseExperienceByName(String name) {
        PokemonDto pokemon = webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/pokemon/{name}")
                        .build(name))
                .retrieve()
                .bodyToMono(PokemonDto.class)
                .block();
        return Mono.just(pokemon.baseExperience);
    }

    @Override
    public Flux<HeldItem> findHeldItems(String name) {
        PokemonDto pokemon = webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/pokemon/{name}")
                        .build(name))
                .retrieve()
                .bodyToMono(PokemonDto.class)
                .block();
        return Flux.fromIterable(pokemon.heldItems.stream()
                .map(dto -> mapToHeldItem(dto))
                .collect(Collectors.toList()));
    }

    private HeldItem mapToHeldItem(HeldItemDto dto) {
        var heldItem = new HeldItem();
        heldItem.setName(dto.item.get("name"));
        heldItem.setDetails(dto.versionDetails.stream()
                .map(d -> mapToDetail(d))
                .collect(Collectors.toList()));
        return heldItem;
    }

    private HeldDetail mapToDetail(VersionDetailDto d) {
        var heldDetail = new HeldDetail();
        heldDetail.setName(d.version.get("name"));
        heldDetail.setRarity(d.rarity);
        return heldDetail;
    }

    @Override
    public Mono<Long> findIdByName(String pokemonName) {
        PokemonDto pokemon = webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/pokemon/{name}")
                        .build(pokemonName))
                .retrieve()
                .bodyToMono(PokemonDto.class)
                .block();
        return Mono.just(pokemon.id);
    }

    @Override
    public Mono<String> findName(String name) {
        PokemonDto pokemon = webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/pokemon/{name}")
                        .build(name))
                .retrieve()
                .bodyToMono(PokemonDto.class)
                .block();
        return Mono.just(pokemon.name);
    }

    @Override
    public Flux<Encounter> findLocationEncountersById(Long id) {

        EncounterDto[] encounterDtos = webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/pokemon/{id}/encounters")
                        .build(id))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(EncounterDto[].class)
                .block();
        List<Encounter> encounters = Arrays.asList(encounterDtos)
                .stream()
                .map(dto -> mapToEncounter(dto))
                .collect(Collectors.toList());

        return Flux.fromIterable(encounters);
    }

    private Encounter mapToEncounter(EncounterDto e) {
        var encounter = new Encounter();
        encounter.setLocationArea(e.locationArea.get("name"));
        encounter.setVersionDetails(e.versionDetails
                .stream()
                .map(vdtl -> mapToVersionDetails(vdtl))
                .collect(Collectors.toList()));
        return encounter;
    }

    private VersionDetail mapToVersionDetails(EncounterVersionDetailDto vdtl) {
        var versionDetail = new VersionDetail();
        versionDetail.setName(vdtl.getVersion().get("name"));
        versionDetail.setMaxChance(vdtl.getMaxChance());
        versionDetail.setEncounterDetails(vdtl.getEncounterDetailDtos()
                .stream()
                .map(edtl -> mapToEncounterDetail(edtl))
                .collect(Collectors.toList()));
        return versionDetail;
    }


    private EncounterDetail mapToEncounterDetail(EncounterDetailDto encounterDetailDto) {
        var encounterDetail = new EncounterDetail();

        encounterDetail.setChance(encounterDetailDto.chance);
        //encounterDetail.setConditionValues(d.conditionValues.get("name"));
        encounterDetail.setMethod(encounterDetailDto.method.get("name"));
        encounterDetail.setMinLevel(encounterDetailDto.minLevel);
        encounterDetail.setMaxLevel(encounterDetailDto.maxLevel);
        return encounterDetail;
    }

    private Ability mapToAbility(AbilityDto abilityDto) {
        return new Ability(abilityDto.getAbility().get("name"),abilityDto.hidden,abilityDto.slot);
    }
}
