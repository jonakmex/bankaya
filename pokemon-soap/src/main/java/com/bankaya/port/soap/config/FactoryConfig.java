package com.bankaya.port.soap.config;

import com.bankaya.pokemon.boundary.HelloWorldRequest;
import com.bankaya.pokemon.boundary.RequestFactory;
import com.bankaya.pokemon.usecase.UseCase;
import com.bankaya.pokemon.usecase.UseCaseFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryConfig {

    private ApplicationContext applicationContext;

    public FactoryConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public UseCaseFactory useCaseFactory(){
        return useCaseName -> (UseCase) applicationContext.getBean(useCaseName);
    }

    @Bean
    public RequestFactory requestFactory(){
        return (requestName,params) -> {
            HelloWorldRequest helloWorldRequest = new HelloWorldRequest();
            helloWorldRequest.name = params.get("name").toString();
            return helloWorldRequest;
        };
    }
}
