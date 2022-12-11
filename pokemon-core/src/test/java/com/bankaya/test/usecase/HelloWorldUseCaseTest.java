package com.bankaya.test.usecase;

import com.bankaya.pokemon.boundary.HelloWorldRequest;
import com.bankaya.pokemon.boundary.HelloWorldResponse;
import com.bankaya.pokemon.boundary.Request;
import com.bankaya.pokemon.boundary.RequestFactory;
import com.bankaya.pokemon.usecase.HelloWorldUseCase;
import com.bankaya.pokemon.usecase.UseCase;
import com.bankaya.pokemon.usecase.UseCaseFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class HelloWorldUseCaseTest {
    private static final Logger logger = LoggerFactory.getLogger(HelloWorldUseCaseTest.class);
    @Mock
    private UseCaseFactory useCaseFactory;
    @Mock
    private RequestFactory requestFactory;

    @BeforeEach
    public void setup(){
        lenient().when(useCaseFactory.make(anyString())).thenReturn(new HelloWorldUseCase());
        lenient().when(requestFactory.make(anyString(),anyMap())).thenAnswer(i -> {
            Map<String,Object> arg = (Map)i.getArgument(1);
            HelloWorldRequest helloWorldRequest = new HelloWorldRequest();
            helloWorldRequest.name = arg.get("name").toString();
            return helloWorldRequest;
        });
    }

    @Test
    public void should_execute_use_case(){
        UseCase useCase = useCaseFactory.make("HelloWorldUseCase");
        Request request = requestFactory.make("HelloWorldRequest", Collections.singletonMap("name", "Javier"));
        useCase.execute(request)
                .map(response -> (HelloWorldResponse)response)
                .subscribe(response -> logger.debug(response.greeting));
    }

    @Test
    public void test2(){
        logger.debug("Test 2");
    }
}
