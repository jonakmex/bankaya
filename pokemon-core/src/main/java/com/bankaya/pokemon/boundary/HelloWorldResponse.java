package com.bankaya.pokemon.boundary;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class HelloWorldResponse extends Response {
    public String greeting;
}
