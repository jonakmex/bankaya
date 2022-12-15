package com.bankaya.test.impl;

import com.bankaya.pokemon.boundary.RequestFactory;
import com.bankaya.pokemon.boundary.request.*;

import java.util.Map;


public class RequestFactoryImpl implements RequestFactory {
    @Override
    public Request make(String requestName, Map<String, Object> params) {
        switch (requestName){
            case "FindAbilitiesRequest":
                return makeFindAbilitiesRequest(params);
            case "FindBaseExperienceRequest":
                return makeFindBaseExperienceRequest(params);
            case "FindIdRequest":
                return makeFindIdRequest(params);
            case "FindNameRequest":
                return makeFindNameRequest(params);
            case "FindHeldItemsRequest":
                return makeFindHeldItemsRequest(params);
            case "FindLocationEncountersRequest":
                return makeFindLocationEncountersRequest(params);
            default:
                return null;
        }
    }

    private Request makeFindLocationEncountersRequest(Map<String, Object> params) {
        var findLocationEncounters = new FindLocationEncountersRequest();
        findLocationEncounters.name = params.get("name").toString();
        return findLocationEncounters;
    }

    private Request makeFindHeldItemsRequest(Map<String, Object> params) {
        var findHeldItemsRequest = new FindHeldItemsRequest();
        findHeldItemsRequest.name = params.get("name").toString();
        return findHeldItemsRequest;
    }

    private Request makeFindNameRequest(Map<String, Object> params) {
        var findNameRequest = new FindNameRequest();
        findNameRequest.name = params.get("name").toString();
        return findNameRequest;
    }

    private Request makeFindIdRequest(Map<String, Object> params) {
        var findIdRequest = new FindIdRequest();
        findIdRequest.name = params.get("name").toString();
        return findIdRequest;
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
