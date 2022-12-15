package com.bankaya.pokemon.boundary.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class FindNameResponse extends Response {
    public String name;
}
