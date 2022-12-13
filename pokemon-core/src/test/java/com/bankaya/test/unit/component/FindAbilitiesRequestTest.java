package com.bankaya.test.unit.component;

import com.bankaya.pokemon.boundary.request.FindAbilitiesRequest;
import com.bankaya.pokemon.boundary.request.HelloWorldRequest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FindAbilitiesRequestTest {
    private static final Logger logger = LoggerFactory.getLogger(FindAbilitiesRequestTest.class);
    @Test
    public void should_be_valid_name(){
        FindAbilitiesRequest findAbilitiesRequest = new FindAbilitiesRequest();
        findAbilitiesRequest.name = "ditto";
        assertTrue(findAbilitiesRequest.validate().isEmpty());
    }

    @Test
    public void should_return_too_long_error(){
        FindAbilitiesRequest findAbilitiesRequest = new FindAbilitiesRequest();
        findAbilitiesRequest.name = "Jonathanlkj sdlkjs flsjd flksjd flsjd flksdjf lksjd flsjdf lksdj flsdj flskdjf lsdkjf ";
        Map<String,String> errors = findAbilitiesRequest.validate();

        assertTrue(errors.containsKey("name"));
        assertEquals(errors.get("name"),"MSG_ERR_002");
    }

    @Test
    public void should_return_too_short_error(){
        FindAbilitiesRequest findAbilitiesRequest = new FindAbilitiesRequest();
        findAbilitiesRequest.name = "";
        Map<String,String> errors = findAbilitiesRequest.validate();

        assertTrue(errors.containsKey("name"));
        assertEquals(errors.get("name"),"MSG_ERR_001");
    }
}
