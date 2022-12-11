package com.bankaya.test.unit;

import com.bankaya.pokemon.boundary.HelloWorldRequest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HelloWorldRequestValidationTest {
    private static final Logger logger = LoggerFactory.getLogger(HelloWorldRequestValidationTest.class);

    @Test
    public void should_be_valid(){
        HelloWorldRequest helloWorldRequest = new HelloWorldRequest();
        helloWorldRequest.name = "Jonathan";
        assertTrue(helloWorldRequest.validate().isEmpty());
    }

    @Test
    public void should_return_too_long_error(){
        HelloWorldRequest helloWorldRequest = new HelloWorldRequest();
        helloWorldRequest.name = "Jonathanlkj sdlkjs flsjd flksjd flsjd flksdjf lksjd flsjdf lksdj flsdj flskdjf lsdkjf ";
        Map<String,String> errors = helloWorldRequest.validate();

        assertTrue(errors.containsKey("name"));
        assertEquals(errors.get("name"),"MSG_ERR_002");
    }

    @Test
    public void should_return_too_short_error(){
        HelloWorldRequest helloWorldRequest = new HelloWorldRequest();
        helloWorldRequest.name = "";
        Map<String,String> errors = helloWorldRequest.validate();

        assertTrue(errors.containsKey("name"));
        assertEquals(errors.get("name"),"MSG_ERR_001");
    }
}
