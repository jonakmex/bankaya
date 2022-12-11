package com.bankaya.pokemon.usecase;

import com.bankaya.pokemon.boundary.Request;
import com.bankaya.pokemon.boundary.Response;
import reactor.core.publisher.Mono;
@FunctionalInterface
public interface UseCase {
    Mono<Response> execute(Request request);
}
