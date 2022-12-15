package com.bankaya.port.soap.gateway;

import com.bankaya.pokemon.entity.Ability;
import com.bankaya.pokemon.entity.HeldItem;
import com.bankaya.pokemon.gateway.PokemonGateway;
import com.bankaya.port.soap.gateway.dto.AbilityDto;
import com.bankaya.port.soap.gateway.dto.PokemonDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
        return null;
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

    private Ability mapToAbility(AbilityDto abilityDto) {
        return new Ability(abilityDto.getAbility().get("name"),abilityDto.hidden,abilityDto.slot);
    }
}
