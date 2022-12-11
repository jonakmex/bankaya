package com.bankaya.test;

import com.bankaya.pokemon.boundary.HelloWorldRequest;
import com.bankaya.pokemon.boundary.HelloWorldResponse;
import com.bankaya.pokemon.boundary.Request;
import com.bankaya.pokemon.boundary.RequestFactory;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;

public class StepDefinitions {
    private UseCaseFactory useCaseFactory;
    private RequestFactory requestFactory;
    private Request request;
    private String result;

    @Before
    public void beforeScenario(){
        requestFactory = Mockito.mock(RequestFactory.class);
        Mockito.when(requestFactory.make(anyString(),anyMap())).thenAnswer(i -> {
            Map arg = i.getArgument(1,Map.class);
            HelloWorldRequest helloWorldRequest = new HelloWorldRequest();
            helloWorldRequest.name = arg.get("name").toString();
            return helloWorldRequest;
        });

        useCaseFactory = Mockito.mock(UseCaseFactory.class);
        Mockito.when(useCaseFactory.make(anyString())).thenReturn(new HelloWorldUseCase());
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

