package com.bankaya.port.soap.handler;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class PokemonHandler {
    public Mono<ServerResponse> hello(ServerRequest serverRequest){
        return ServerResponse.ok().build();
    }
}
