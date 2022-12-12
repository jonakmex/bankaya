package com.bankaya.pokemon.boundary;

import com.bankaya.pokemon.boundary.request.Request;

import java.util.Map;

public interface RequestFactory {
    Request make(String requestName, Map<String,Object> params);
}
