package com.bankaya.pokemon.usecase;

import com.bankaya.pokemon.boundary.request.Request;
import com.bankaya.pokemon.boundary.response.Response;
import reactor.core.publisher.Mono;
@FunctionalInterface
public interface UseCase {
    Mono<Response> execute(Request request);
}
