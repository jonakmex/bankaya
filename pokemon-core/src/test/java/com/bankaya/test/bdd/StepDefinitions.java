package com.bankaya.test.bdd;

import com.bankaya.pokemon.boundary.RequestFactory;
import com.bankaya.pokemon.boundary.request.HelloWorldRequest;
import com.bankaya.pokemon.boundary.request.Request;
import com.bankaya.pokemon.boundary.response.HelloWorldResponse;
import com.bankaya.pokemon.gateway.HelloGateway;
import com.bankaya.pokemon.usecase.HelloWorldUseCase;
import com.bankaya.pokemon.usecase.UseCase;
import com.bankaya.pokemon.usecase.UseCaseFactory;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;

public class StepDefinitions {
    private UseCaseFactory useCaseFactory;
    private RequestFactory requestFactory;
    private HelloGateway helloGateway;
    private Request request;
    private String result;

    @Before
    public void beforeScenario(){
        requestFactory = Mockito.mock(RequestFactory.class);
        useCaseFactory = Mockito.mock(UseCaseFactory.class);
        helloGateway = Mockito.mock(HelloGateway.class);

        Mockito.when(requestFactory.make(anyString(),anyMap())).thenAnswer(i -> {
            Map arg = i.getArgument(1,Map.class);
            HelloWorldRequest helloWorldRequest = new HelloWorldRequest();
            helloWorldRequest.name = arg.get("name").toString();
            return helloWorldRequest;
        });

        Mockito.when(helloGateway.retrieveGreeting(anyString())).thenAnswer(i -> {
            String name = i.getArgument(0);
            return "Hello "+name;
        });
        Mockito.when(useCaseFactory.make(anyString())).thenReturn(new HelloWorldUseCase(helloGateway));
    }

    @Given("My Name is Jonathan")
    public void my_name_is_jonathan() {
        request = requestFactory.make("HelloWorldRequest", Collections.singletonMap("name","Jonathan"));
    }
    @When("I ask for the greeting")
    public void i_ask_for_the_greeting() {
        UseCase useCase = useCaseFactory.make("HelloWorldRequest");
        useCase.execute(request)
                .map(r -> (HelloWorldResponse)r)
                .subscribe(response -> result = response.greeting);
    }
    @Then("I should be told Hello Jonathan")
    public void i_should_be_told_hello_jonathan() {
        assertEquals("Hello Jonathan",result);
    }
}

