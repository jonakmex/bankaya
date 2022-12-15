package com.bankaya.port.soap.factory;

import com.bankaya.pokemon.boundary.RequestFactory;
import com.bankaya.pokemon.boundary.request.*;
import org.springframework.stereotype.Component;


import java.util.Map;
@Component
public class RequestFactoryImpl implements RequestFactory {

    @Override
    public Request make(String requestName, Map<String, Object> params) {
        switch(requestName){
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
            default:
                return null;
        }
    }

    private Request makeFindHeldItemsRequest(Map<String, Object> params) {
        FindHeldItemsRequest findHeldItemsRequest = new FindHeldItemsRequest();
        findHeldItemsRequest.setName(params.get("name").toString());
        return findHeldItemsRequest;
    }

    private Request makeFindNameRequest(Map<String, Object> params) {
        FindNameRequest findNameRequest = new FindNameRequest();
        findNameRequest.setName(params.get("name").toString());
        return findNameRequest;
    }

    private Request makeFindIdRequest(Map<String, Object> params) {
        FindIdRequest findIdRequest = new FindIdRequest();
        findIdRequest.setName(params.get("name").toString());
        return findIdRequest;
    }

    private Request makeFindBaseExperienceRequest(Map<String, Object> params) {
        FindBaseExperienceRequest findBaseExperienceRequest = new FindBaseExperienceRequest();
        findBaseExperienceRequest.setName(params.get("name").toString());
        return findBaseExperienceRequest;
    }

    private Request makeFindAbilitiesRequest(Map<String, Object> params) {
        FindAbilitiesRequest findAbilitiesRequest = new FindAbilitiesRequest();
        findAbilitiesRequest.setName(params.get("name").toString());
        return findAbilitiesRequest;
    }
}
