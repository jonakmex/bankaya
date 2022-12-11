package com.bankaya.port.soap.endpoint;

import com.bankaya.pokemon.boundary.HelloWorldResponse;
import com.bankaya.pokemon.boundary.Request;
import com.bankaya.pokemon.boundary.RequestFactory;
import com.bankaya.pokemon.usecase.UseCase;
import com.bankaya.pokemon.usecase.UseCaseFactory;
import com.bankaya.pokemon_web_service.HelloRequest;
import com.bankaya.pokemon_web_service.HelloResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Collections;

@Endpoint
public class PokemonEndpoint {
    private static final String NAMESPACE_URI = "http://bankaya.com/pokemon-web-service";
    private UseCaseFactory useCaseFactory;
    private RequestFactory requestFactory;

    public PokemonEndpoint(UseCaseFactory useCaseFactory, RequestFactory requestFactory) {
        this.useCaseFactory = useCaseFactory;
        this.requestFactory = requestFactory;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "helloRequest")
    @ResponsePayload
    public HelloResponse hello(@RequestPayload HelloRequest request) {
        UseCase useCase = useCaseFactory.make("HelloWorldUseCase");
        Request helloWorldRequest = requestFactory.make("", Collections.singletonMap("name", "Jonathan"));
        HelloResponse response = new HelloResponse();
        useCase.execute(helloWorldRequest)
                .map(r -> (HelloWorldResponse)r)
                .subscribe(helloWorldResponse -> response.setGreeting(helloWorldResponse.greeting));
        return response;
    }
}
