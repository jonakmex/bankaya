package com.bankaya.port.soap.endpoint;

import com.bankaya.pokemon_web_service.HelloRequest;
import com.bankaya.pokemon_web_service.HelloResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PokemonEndpoint {
    private static final String NAMESPACE_URI = "http://bankaya.com/pokemon-web-service";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "helloRequest")
    @ResponsePayload
    public HelloResponse getCountry(@RequestPayload HelloRequest request) {
        HelloResponse response = new HelloResponse();
        response.setGreeting("Hello World");
        return response;
    }
}
