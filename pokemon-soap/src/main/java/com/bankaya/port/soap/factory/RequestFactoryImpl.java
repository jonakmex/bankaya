package com.bankaya.port.soap.factory;

import com.bankaya.pokemon.boundary.RequestFactory;
import com.bankaya.pokemon.boundary.request.FindAbilitiesRequest;
import com.bankaya.pokemon.boundary.request.FindBaseExperienceRequest;
import com.bankaya.pokemon.boundary.request.Request;
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
            default:
                return null;
        }
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
