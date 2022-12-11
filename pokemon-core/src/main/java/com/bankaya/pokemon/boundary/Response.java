package com.bankaya.pokemon.boundary;

import lombok.Data;

import java.util.Map;
@Data
public class Response {
    public boolean success;
    public Map<String,String> errors;

    public static Response makeFailResponse(Map<String,String> errors ){
        Response response = new Response();
        response.success = false;
        response.errors = errors;
        return response;
    }
}
