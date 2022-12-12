package com.bankaya.pokemon.boundary.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class HelloWorldResponse extends Response {
    public String greeting;
}
