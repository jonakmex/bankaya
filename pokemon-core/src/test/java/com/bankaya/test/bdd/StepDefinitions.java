package com.bankaya.test.bdd;

import com.bankaya.pokemon.boundary.RequestFactory;
import com.bankaya.pokemon.boundary.ds.AbilityDS;
import com.bankaya.pokemon.boundary.request.Request;
import com.bankaya.pokemon.boundary.response.FindAbilitiesResponse;
import com.bankaya.pokemon.boundary.response.HelloWorldResponse;
import com.bankaya.pokemon.entity.Pokemon;
import com.bankaya.pokemon.gateway.HelloGateway;
import com.bankaya.pokemon.gateway.PokemonGateway;
import com.bankaya.pokemon.usecase.UseCase;
import com.bankaya.pokemon.usecase.UseCaseFactory;
import com.bankaya.test.impl.RequestFactoryImpl;
import com.bankaya.test.impl.UseCaseFactoryImpl;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.*;

import static java.util.Collections.singletonMap;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

public class StepDefinitions {
    private static final Logger logger = LoggerFactory.getLogger(StepDefinitions.class);
    private UseCaseFactory useCaseFactory;
    private RequestFactory requestFactory;
    private Map<String,Object> scenarioContext;

    @Before
    public void beforeScenario(){
        requestFactory = new RequestFactoryImpl();
        useCaseFactory = new UseCaseFactoryImpl();


        HelloGateway helloGateway = Mockito.mock(HelloGateway.class);
        Mockito.when(helloGateway.retrieveGreeting(anyString())).thenAnswer(i -> {
            String name = i.getArgument(0);
            return "Hello "+name;
        });
        ((UseCaseFactoryImpl)useCaseFactory).getContext().put("helloGateway",helloGateway);

        PokemonGateway pokemonGateway = Mockito.mock(PokemonGateway.class);
        Mockito.when(pokemonGateway.findAbilitiesByName(anyString())).thenAnswer(i->{
            String name = i.getArgument(0);
            List<Pokemon> pokemons = (List<Pokemon>) scenarioContext.get("pokemons");
             Optional<Pokemon> found = pokemons.stream()
                    .filter(p -> p.getName().equals(name))
                     .findFirst();
             if(!found.isEmpty())
                 return Flux.fromIterable(found.get().getAbilities());
             else
                 return Flux.empty();

        });
        ((UseCaseFactoryImpl)useCaseFactory).getContext().put("pokemonGateway",pokemonGateway);

        scenarioContext = new HashMap<>();

    }

    @Given("My Name is Jonathan")
    public void my_name_is_jonathan() {
        Request request = requestFactory.make("HelloWorldRequest", singletonMap("name","Jonathan"));
        scenarioContext.put("helloWorldRequest",request);
    }
    @When("I ask for the greeting")
    public void i_ask_for_the_greeting() {
        UseCase useCase = useCaseFactory.make("HelloWorldUseCase");
        useCase.execute((Request)scenarioContext.get("helloWorldRequest"))
                .map(r -> (HelloWorldResponse)r)
                .subscribe(response -> scenarioContext.put("result",response.greeting));
    }
    @Then("I should be told Hello Jonathan")
    public void i_should_be_told_hello_jonathan() {
        assertEquals("Hello Jonathan", scenarioContext.get("result"));
    }

    @Given("There is a pokemon called ditto")
    public void there_is_a_pokemon_called() {
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(Utils.newPokemonWithAbilities("ditto"));
        scenarioContext.put("pokemons",pokemons);
        scenarioContext.put("pokemonName","ditto");
    }
    @When("I retrieve it's abilities")
    public void i_retrieve_it_s_abilities() {
        UseCase useCase = useCaseFactory.make("FindAbilitiesUseCase");
        Request request = requestFactory.make("FindAbilitiesRequest", singletonMap("name", scenarioContext.get("pokemonName")));
        useCase.execute(request)
                .map(response -> (FindAbilitiesResponse)response)
                .subscribe(response -> scenarioContext.put("abilities",response.abilities));
    }
    @Then("I should get a list of Abilities")
    public void i_get_a_list_of_abilities() {
        List<AbilityDS> abilities = (List<AbilityDS>) scenarioContext.get("abilities");
        assertNotNull(abilities);
        assertTrue(!abilities.isEmpty());
        abilities.stream().forEach(a -> logger.debug(a.name));
    }
}

