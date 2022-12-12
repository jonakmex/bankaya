package com.bankaya.pokemon.usecase;

import com.bankaya.pokemon.boundary.request.HelloWorldRequest;
import com.bankaya.pokemon.boundary.response.HelloWorldResponse;
import com.bankaya.pokemon.boundary.request.Request;
import com.bankaya.pokemon.boundary.response.Response;
import com.bankaya.pokemon.gateway.HelloGateway;
import reactor.core.publisher.Mono;

import java.util.Map;


public class HelloWorldUseCase implements UseCase {

    private HelloGateway helloGateway;

    public HelloWorldUseCase(HelloGateway helloGateway) {
        this.helloGateway = helloGateway;
    }

    @Override
    public Mono<Response> execute(Request request) {
        Map<String,String> errors = request.validate();
        if(!errors.isEmpty())
            return Mono.just(Response.makeFailResponse(errors));

        HelloWorldRequest helloWorldRequest = (HelloWorldRequest) request;
        HelloWorldResponse response = new HelloWorldResponse();
        response.success = true;
        response.greeting = "Hello " + (helloWorldRequest.name != null ? helloWorldRequest.name : "World");
        return Mono.just(response);
    }
}
