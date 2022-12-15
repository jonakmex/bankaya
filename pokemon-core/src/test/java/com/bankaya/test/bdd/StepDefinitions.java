package com.bankaya.test.bdd;

import com.bankaya.pokemon.boundary.RequestFactory;
import com.bankaya.pokemon.boundary.ds.AbilityDS;
import com.bankaya.pokemon.boundary.request.Request;
import com.bankaya.pokemon.boundary.response.FindAbilitiesResponse;
import com.bankaya.pokemon.boundary.response.FindBaseExperienceResponse;
import com.bankaya.pokemon.boundary.response.FindIdResponse;
import com.bankaya.pokemon.entity.Pokemon;
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
import reactor.core.publisher.Mono;

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

        Mockito.when(pokemonGateway.findBaseExperienceByName(anyString())).thenAnswer(i->{
            String name = i.getArgument(0);
            List<Pokemon> pokemons = (List<Pokemon>) scenarioContext.get("pokemons");
            Optional<Pokemon> found = pokemons.stream()
                    .filter(p -> p.getName().equals(name))
                    .findFirst();
            if(!found.isEmpty())
                return Mono.just(found.get().getBaseExperience());
            else
                return Mono.empty();

        });

        Mockito.when(pokemonGateway.findIdByName(anyString())).thenAnswer(i->{
            String name = i.getArgument(0);
            List<Pokemon> pokemons = (List<Pokemon>) scenarioContext.get("pokemons");
            Optional<Pokemon> found = pokemons.stream()
                    .filter(p -> p.getName().equals(name))
                    .findFirst();
            if(!found.isEmpty())
                return Mono.just(found.get().getId());
            else
                return Mono.empty();

        });

        ((UseCaseFactoryImpl)useCaseFactory).getContext().put("pokemonGateway",pokemonGateway);

        scenarioContext = new HashMap<>();

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
        abilities.stream().forEach(a -> logger.debug(a.slot+""));
    }

    @Given("There is not a pokemon called xyz")
    public void there_is_not_a_pokemon_called_xyz() {
        List<Pokemon> pokemons = new ArrayList<>();
        scenarioContext.put("pokemons",pokemons);
        scenarioContext.put("pokemonName","xyz");
    }
    @Then("I should get an empty list of abilities")
    public void i_should_get_an_empty_list_of_abilities() {
        List<AbilityDS> abilities = (List<AbilityDS>) scenarioContext.get("abilities");
        assertNotNull(abilities);
        assertTrue(abilities.isEmpty());
    }

    @When("I retrieve it's base experience")
    public void i_retrieve_it_s_base_experience() {
        UseCase useCase = useCaseFactory.make("FindBaseExperienceUseCase");
        Request request = requestFactory.make("FindBaseExperienceRequest", singletonMap("name", scenarioContext.get("pokemonName")));
        useCase.execute(request)
                .map(response -> (FindBaseExperienceResponse)response)
                .subscribe(response -> scenarioContext.put("base_experience",response.baseExperience));
    }
    @Then("I should get it's base experience")
    public void i_should_get_it_s_base_experience() {
        logger.debug(scenarioContext.get("base_experience").toString());
        assertNotNull(scenarioContext.get("base_experience"));
    }

    @Then("I should get an empty result")
    public void i_should_get_an_empty_result() {
        assertNull(scenarioContext.get("base_experience"));
    }

    @When("I retrieve it's id")
    public void i_retrieve_it_s_id() {
        UseCase useCase = useCaseFactory.make("FindIdUseCase");
        Request request = requestFactory.make("FindIdRequest", singletonMap("name", scenarioContext.get("pokemonName")));
        useCase.execute(request)
                .map(response -> (FindIdResponse)response)
                .subscribe(response -> scenarioContext.put("id",response.id));
    }
    @Then("I should get it's id")
    public void i_should_get_it_s_id() {
        assertNull(scenarioContext.get("base_experience"));
    }
}

