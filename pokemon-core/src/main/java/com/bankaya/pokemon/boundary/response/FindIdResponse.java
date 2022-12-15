package com.bankaya.pokemon.boundary.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class FindIdResponse extends Response {
    public Long id;
}
