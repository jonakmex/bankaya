package com.bankaya.test.impl;

import com.bankaya.pokemon.boundary.RequestFactory;
import com.bankaya.pokemon.boundary.request.HelloWorldRequest;
import com.bankaya.pokemon.boundary.request.Request;

import java.util.Map;


public class RequestFactoryImpl implements RequestFactory {
    @Override
    public Request make(String requestName, Map<String, Object> params) {
        switch (requestName){
            case "HelloWorldRequest":
                return makeHelloWorldRequest(params);
            default:
                return null;
        }
    }

    private Request makeHelloWorldRequest(Map<String, Object> params) {
        HelloWorldRequest helloWorldRequest = new HelloWorldRequest();
        helloWorldRequest.name = params.get("name").toString();
        return helloWorldRequest;
    }
}
