package com.bankaya.pokemon.boundary;

import java.util.Map;

public interface RequestFactory {
    Request make(String requestName, Map<String,Object> params);
}
