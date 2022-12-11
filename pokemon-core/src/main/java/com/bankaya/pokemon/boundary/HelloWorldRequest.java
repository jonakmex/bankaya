package com.bankaya.pokemon.boundary;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper=false)
public class HelloWorldRequest extends Request {
    public String name;

    @Override
    public Map<String, String> validate() {
        Map<String,String> errors = new HashMap<>();
        if(name == null || name.length()<1)
            errors.put("name","MSG_ERR_001");

        if(name.length()>30)
            errors.put("name","MSG_ERR_002");

        return errors;
    }
}
