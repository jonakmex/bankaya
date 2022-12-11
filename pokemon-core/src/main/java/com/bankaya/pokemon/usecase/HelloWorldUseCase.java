package com.bankaya.pokemon.usecase;

import com.bankaya.pokemon.boundary.HelloWorldResponse;
import com.bankaya.pokemon.boundary.Request;
import com.bankaya.pokemon.boundary.Response;
import lombok.EqualsAndHashCode;
import reactor.core.publisher.Mono;

@EqualsAndHashCode(callSuper=false)
public class HelloWorldUseCase implements UseCase {
    @Override
    public Mono<Response> execute(Request request) {
        HelloWorldResponse response = new HelloWorldResponse();
        response.greeting = "Hello World";
        return Mono.just(response);
    }
}
