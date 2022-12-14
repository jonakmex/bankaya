package com.bankaya.test.impl;

import com.bankaya.pokemon.boundary.RequestFactory;
import com.bankaya.pokemon.boundary.request.FindAbilitiesRequest;
import com.bankaya.pokemon.boundary.request.FindBaseExperienceRequest;
import com.bankaya.pokemon.boundary.request.Request;

import java.util.Map;


public class RequestFactoryImpl implements RequestFactory {
    @Override
    public Request make(String requestName, Map<String, Object> params) {
        switch (requestName){
            case "FindAbilitiesRequest":
                return makeFindAbilitiesRequest(params);
            case "FindBaseExperienceRequest":
                return makeFindBaseExperienceRequest(params);
            default:
                return null;
        }
    }

    private Request makeFindBaseExperienceRequest(Map<String, Object> params) {
        var findBaseExperienceRequest = new FindBaseExperienceRequest();
        findBaseExperienceRequest.name = params.get("name").toString();
        return findBaseExperienceRequest;
    }

    private Request makeFindAbilitiesRequest(Map<String, Object> params) {
        var findAbilitiesRequest = new FindAbilitiesRequest();
        findAbilitiesRequest.name = params.get("name").toString();
        return findAbilitiesRequest;
    }

}
