package com.bankaya.port.soap.router;

import com.bankaya.port.soap.handler.PokemonHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class PokemonRouter {
    @Bean
    public RouterFunction<ServerResponse> doRoute(PokemonHandler pokemonHandler){
        return RouterFunctions.route(
                        GET("/hello").and(accept(MediaType.APPLICATION_JSON)), pokemonHandler::hello);
    }
}
