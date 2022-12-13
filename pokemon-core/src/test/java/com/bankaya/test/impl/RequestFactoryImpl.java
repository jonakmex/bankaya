package com.bankaya.test.impl;

import com.bankaya.pokemon.boundary.RequestFactory;
import com.bankaya.pokemon.boundary.request.FindAbilitiesRequest;
import com.bankaya.pokemon.boundary.request.HelloWorldRequest;
import com.bankaya.pokemon.boundary.request.Request;

import java.util.Map;


public class RequestFactoryImpl implements RequestFactory {
    @Override
    public Request make(String requestName, Map<String, Object> params) {
        switch (requestName){
            case "HelloWorldRequest":
                return makeHelloWorldRequest(params);
            case "FindAbilitiesRequest":
                return makeFindAbilitiesRequest(params);
            default:
                return null;
        }
    }

    private Request makeFindAbilitiesRequest(Map<String, Object> params) {
        var findAbilitiesRequest = new FindAbilitiesRequest();
        findAbilitiesRequest.name = params.get("name").toString();
        return findAbilitiesRequest;
    }

    private Request makeHelloWorldRequest(Map<String, Object> params) {
        var helloWorldRequest = new HelloWorldRequest();
        helloWorldRequest.name = params.get("name").toString();
        return helloWorldRequest;
    }
}
