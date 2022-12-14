package com.bankaya.test.impl;

import com.bankaya.pokemon.gateway.PokemonGateway;
import com.bankaya.pokemon.usecase.*;

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
            case "FindAbilitiesUseCase":
                return makeFindAbilitiesUseCase();
            case "FindBaseExperienceUseCase":
                return makeFindBaseExperienceUseCase();
            case "FindIdUseCase":
                return makeFindIdUseCase();
            case "FindNameUseCase":
                return makeFindNameUseCase();
            case "FindHeldItemsUseCase":
                return makeFindHeldItemsUseCase();
            case "FindLocationEncountersUseCase":
                return makeFindLocationEncountersUseCase();
            default:
                return null;
        }
    }

    private UseCase makeFindLocationEncountersUseCase() {
        if(context.get("FindLocationEncountersUseCase") == null)
            context.put("FindLocationEncountersUseCase", new FindLocationEncountersUseCase((PokemonGateway) context.get("pokemonGateway"),(PokemonGateway) context.get("pokemonGateway")));

        return (UseCase) context.get("FindLocationEncountersUseCase");
    }

    private UseCase makeFindHeldItemsUseCase() {
        if(context.get("FindHeldItemsUseCase") == null)
            context.put("FindHeldItemsUseCase", new FindHeldItemsUseCase((PokemonGateway) context.get("pokemonGateway")));

        return (UseCase) context.get("FindHeldItemsUseCase");
    }

    private UseCase makeFindNameUseCase() {
        if(context.get("FindNameUseCase") == null)
            context.put("FindNameUseCase", new FindNameUseCase((PokemonGateway) context.get("pokemonGateway")));

        return (UseCase) context.get("FindNameUseCase");
    }

    private UseCase makeFindIdUseCase() {
        if(context.get("FindIdUseCase") == null)
            context.put("FindIdUseCase", new FindIdUseCase((PokemonGateway) context.get("pokemonGateway")));

        return (UseCase) context.get("FindIdUseCase");
    }

    private UseCase makeFindBaseExperienceUseCase() {
        if(context.get("FindBaseExperienceUseCase") == null)
            context.put("FindBaseExperienceUseCase", new FindBaseExperienceUseCase((PokemonGateway) context.get("pokemonGateway")));

        return (UseCase) context.get("FindBaseExperienceUseCase");
    }

    private UseCase makeFindAbilitiesUseCase() {
        if(context.get("FindAbilitiesUseCase") == null)
            context.put("FindAbilitiesUseCase", new FindAbilitiesUseCase((PokemonGateway) context.get("pokemonGateway")));

        return (UseCase) context.get("FindAbilitiesUseCase");
    }

}
