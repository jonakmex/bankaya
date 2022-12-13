package com.bankaya.port.soap.factory;

import com.bankaya.pokemon.usecase.UseCase;
import com.bankaya.pokemon.usecase.UseCaseFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class UseCaseFactorySpringContext implements UseCaseFactory {
    private ApplicationContext applicationContext;

    public UseCaseFactorySpringContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public UseCase make(String useCaseName) {
        return (UseCase) applicationContext.getBean(useCaseName);
    }
}
