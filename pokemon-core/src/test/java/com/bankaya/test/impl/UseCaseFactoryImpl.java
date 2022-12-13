package com.bankaya.test.impl;

import com.bankaya.pokemon.gateway.HelloGateway;
import com.bankaya.pokemon.usecase.HelloWorldUseCase;
import com.bankaya.pokemon.usecase.UseCase;
import com.bankaya.pokemon.usecase.UseCaseFactory;

import java.util.HashMap;
import java.util.Map;

public class UseCaseFactoryImpl implements UseCaseFactory {

    private Map<String,Object> context;

    public Map<String, Object> getContext() {
        return context;
    }

    public UseCaseFactoryImpl() {
        context = new HashMap<>();
    }

    @Override
    public UseCase make(String useCaseName) {
        switch (useCaseName){
            case "HelloWorldUseCase":
                return makeHelloWorldUseCase();
            default:
                return null;
        }
    }



    private UseCase makeHelloWorldUseCase() {
        if(context.get("HelloWorldUseCase") == null)
            context.put("HelloWorldUseCase", new HelloWorldUseCase((HelloGateway) context.get("helloGateway") ));


        return (UseCase) context.get("HelloWorldUseCase");
    }
}
