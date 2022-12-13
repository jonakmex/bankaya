package com.bankaya.pokemon.boundary.response;

import com.bankaya.pokemon.boundary.ds.AbilityDS;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class FindAbilitiesResponse extends Response {
    public List<AbilityDS> abilities;
}
